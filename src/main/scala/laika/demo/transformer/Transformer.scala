package laika.demo
package transformer

import laika.api.MarkupParser
import laika.api.Renderer
import laika.ast.CodeBlock
import laika.ast.Paragraph
import laika.ast.Path
import laika.ast.Style
import laika.ast.Styles
import laika.bundle.SyntaxHighlighter
import laika.factory.MarkupFormat
import laika.format.AST
import laika.format.HTML
import laika.format.Markdown
import laika.format.ReStructuredText
import laika.markdown.github.GitHubFlavor
import laika.parse.code.SyntaxHighlighting
import laika.parse.code.languages.HTMLSyntax
import laika.parse.code.languages.LaikaASTSyntax
import laika.parse.markup.DocumentParser.ParserError
import org.scalajs.dom
import org.scalajs.dom.experimental.{Fetch,Request}
import scala.concurrent.Future
import com.raquo.airstream.web.AjaxEventStream
import scala.concurrent.ExecutionContext.Implicits.global
object Transformer {

  def transform(
      input: String,
      format: MarkupFormat,
      on: RunsOn,
      out: OutputFormat
  ): Future[Either[ParserError, String]] = {
    (input, format, on, out) match {
      case (input, format, RunsOn.JS, OutputFormat.RenderedHTML) =>
        Future.successful(Transformer.transformToRenderedHTML(format, input))
      case (input, format, RunsOn.JS, OutputFormat.HTMLSource) =>
        Future.successful(Transformer.transformToHTMLSource(format, input))
      case (input, format, RunsOn.JS, OutputFormat.ResolvedAST) =>
        Future.successful(Transformer.transformToResolvedAST(format, input))
      case (input, format, RunsOn.JS, OutputFormat.UnresolvedAST) =>
        Future.successful(Transformer.transformToUnresolvedAST(format, input))
      case _ => Future.successful(Right("skip"))
    }
  }

  private lazy val parsers: Map[MarkupFormat, MarkupParser] = Map(
    Markdown -> MarkupParser
      .of(Markdown)
      .using(GitHubFlavor, SyntaxHighlighting)
      .build,
    ReStructuredText -> MarkupParser
      .of(ReStructuredText)
      .using(SyntaxHighlighting)
      .build
  )

  private val astRenderer = Renderer.of(AST).build

  private val htmlRenderer = Renderer.of(HTML).build

  private val htmlSourceRenderer = Renderer
    .of(HTML)
    .rendering {
      case (fmt, Paragraph(content, opt)) =>
        fmt.indentedElement("p", opt, content)

      case (fmt, cb @ CodeBlock(lang, content, _, opt)) =>
        val codeStyles =
          if (cb.hasSyntaxHighlighting) Style.noHighlight else Styles(lang)
        "<pre>" + fmt.indentedElement("code", codeStyles, content) + "</pre>"
    }
    .build

  private def highlightAndRender(
      highlighter: SyntaxHighlighter,
      src: String
  ): Either[ParserError, String] =
    highlighter.rootParser
      .parse(src)
      .toEither
      .left
      .map(msg => ParserError(msg, Path.Root))
      .map(CodeBlock(highlighter.language.head, _))
      .map(htmlRenderer.render)

  private def transformToRenderedHTML(
      format: MarkupFormat,
      input: String,
      renderer: Renderer
  ): Either[ParserError, String] =
    parsers(format).parse(input).map(renderer.render)

  def transformToRenderedHTML(
      format: MarkupFormat,
      input: String
  ): Either[ParserError, String] =
    transformToRenderedHTML(format, input, htmlRenderer)

  def transformToHTMLSource(
      format: MarkupFormat,
      input: String
  ): Either[ParserError, String] =
    transformToRenderedHTML(format, input, htmlSourceRenderer).flatMap(
      highlightAndRender(HTMLSyntax, _)
    )

  def transformToUnresolvedAST(
      format: MarkupFormat,
      input: String
  ): Either[ParserError, String] =
    parsers(format)
      .parseUnresolved(input)
      .map(r => astRenderer.render(r.document.content))
      .flatMap(highlightAndRender(LaikaASTSyntax, _))

  def transformToResolvedAST(
      format: MarkupFormat,
      input: String
  ): Either[ParserError, String] =
    parsers(format)
      .parse(input)
      .map(r => astRenderer.render(r.content))
      .flatMap(highlightAndRender(LaikaASTSyntax, _))

}
