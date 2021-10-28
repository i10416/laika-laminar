package laika.demo.components.menu

import com.raquo.laminar.api.L._
import org.scalajs.dom
import scalacss.DevDefaults._
import laika.demo.OutputFormat
import com.raquo.laminar.nodes.ReactiveHtmlElement
import laika.demo.OutputFormat.RenderedHTML
import laika.demo.OutputFormat.HTMLSource
import laika.demo.OutputFormat.ResolvedAST
import laika.demo.OutputFormat.UnresolvedAST
import laika.factory.MarkupFormat
import laika.format.Markdown
import laika.format.ReStructuredText

object Chip {
  def apply(label: String) = div(
    className := MenuStyle.item.className.value,
    label
  )
}

object Toggle {
  def apply(left: String, right: String, fmt: Var[MarkupFormat]) = div(
    className := MenuStyle.toggle.className.value,
    div(className := MenuStyle.left.className.value, left)
      .amend(
        cls.toggle(MenuStyle.active.className.value) <-- fmt.signal
          .map(_ == Markdown)
      )
      .amendThis(thisNode =>
        thisNode.events(onClick).map(_ => Markdown) --> fmt
      ),
    div(className := MenuStyle.right.className.value, right)
      .amend(
        cls.toggle(MenuStyle.active.className.value) <-- fmt.signal
          .map(_ == ReStructuredText)
      )
      .amendThis(thisNode =>
        thisNode.events(onClick).map(_ => ReStructuredText) --> fmt
      )
  )
}

object InputMenu {
  def apply(
      format: Var[MarkupFormat]
  ) = div(
    className := MenuStyle.toggleMenu.className.value,
    Toggle("Markdown", "ReStructuredText", format)
  )
}

object OutputMenu {
  def apply(
      formats: Seq[OutputFormat],
      format: Var[OutputFormat]
  ): ReactiveHtmlElement[org.scalajs.dom.html.Div] = div(
    className := MenuStyle.menu.className.value,
    formats.map {
      case fmt @ OutputFormat(fmtname) =>
        Chip(fmtname.split("-").map(_.capitalize).mkString(" "))
          .amendThis(thisNode => thisNode.events(onClick).map(_ => fmt) -->format)
          .amend(
            cls.toggle(MenuStyle.active.className.value) <-- format.signal.map(_ == fmt)
          )
    }
  )
}
