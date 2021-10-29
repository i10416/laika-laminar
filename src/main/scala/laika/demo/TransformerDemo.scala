package laika.demo
import com.raquo.airstream.combine.generated.CombinableSignal
import com.raquo.laminar.api.L._
import laika.factory.MarkupFormat
import laika.format._
import org.scalajs.dom
import scalacss.DevDefaults._

import scala.concurrent.Future

import components.menu._
import components.inputpanel._
import components.titlebar._
import components.tab._
import transformer.Transformer
import components.outputpanel._

object TransformerDemo {
  implicit val owner = unsafeWindowOwner

  val userInput = Var("")
  val inputMode: Var[MarkupFormat] = Var(initial = Markdown)
  val runsOn: Var[RunsOn] = Var(initial = RunsOn.JS)
  val outputMode: Var[OutputFormat] = Var(initial = OutputFormat.RenderedHTML)

  val result = Signal
    .combine(
      userInput.signal.changes.debounce(600).toSignal(initial = ""),
      inputMode,
      runsOn,
      outputMode
    )
    .changes
    .flatMap { case (i, f, o, out) => Transformer.transform(i, f, o, out) }
    .map {
      case Left(value)  => "err"
      case Right(value) => value
    }
  val root = div(
    className := AppStyle.root.className.value,
    NavBar(),
    Togglable(
      left = InputPanel(userInput, inputMode),
      right = OutputPanel(result, outputMode)
    ),
    Footer()
  )

  def main(args: Array[String]): Unit = {
    val style = org.scalajs.dom.document.createElement("style")
    style.innerHTML = Seq(
      CommonStyle,
      AppStyle,
      InputPanelStyle,
      OutputPanelStyle,
      TogglableStyle,
      MenuStyle
    ).map(_.render[String]).mkString
    dom.document.head.appendChild(style)
    val container = dom.document.getElementById("app")
    render(container, root)
  }
}

object NavBar {
  def apply() = nav(
    className := AppStyle.navBar.className.value,
    h2(
      className := AppStyle.navBarTitle.className.value,
      "Laika Demo"
    )
  )
}

object Footer {
  def apply() = div(
    p("This website is created with Scala, Scala.js"),
    className := AppStyle.footer.className.value,
    div(
      "Powered by ",
      a(
        "Laika",
        href := "https://planet42.github.io/Laika/",
        className := AppStyle.linkText.className.value
      ),
      span(" & "),
      a(
        "Laminar",
        href := "https://laminar.dev/",
        className := AppStyle.linkText.className.value
      )
    )
  )
}

object AppStyle extends StyleSheet.Inline {
  import dsl._
  val root = style(
    backgroundColor := "#f5f6f7",
    margin := "0"
  )
  val title = style(
    fontWeight.bolder,
    fontSize.xxLarge,
    textAlign.center,
    marginTop := 20.px,
    marginBottom := 16.px
  )
  val caption1 = style(
    margin.auto,
    maxWidth := "800px",
    color := "#41464d",
    textAlign.center,
    fontSize := 1.2.em,
    padding := 16.px
  )
  val caption2 = style(
    color := "#41464d",
    textAlign.center,
    fontSize := 0.9.em,
    padding := 8.px
  )
  val linkText = style(
    textDecoration := "none",
    color := "#25c2a1"
  )
  val linkButton = style(
    display.block,
    cursor.pointer,
    padding := 4.px,
    color := "#25c2a1",
    textDecoration := "none",
    fontWeight.bold,
    &.hover {
      opacity := 0.8
    }
  )
  val navBar = style(
    display.flex,
    justifyContent.spaceAround,
    paddingLeft := 18.px,
    paddingRight := 18.px,
    paddingTop := 24.px,
    paddingBottom := 24.px,
    media.only.screen.maxWidth(968.px) {
      justifyContent := "space-between"
    }
  )
  val navBarTitle = style(
    textAlign.left,
    fontSize(18.px),
    color := "#41464d",
    margin := "0",
    padding := "0 24px"
  )
  val navLinks = style(
    display.flex,
    width := 120.px,
    justifyContent.spaceAround
  )
  val footer = style(
    display.flex,
    paddingBottom := 32.px,
    color := "#e8e6e3",
    paddingTop := 16.px,
    flexDirection.column,
    alignItems.center,
    justifyContent.flexEnd,
    backgroundColor := "#303846",
    height := 15.vh
  )
}

object CommonStyle extends StyleSheet.Standalone {
  import dsl._

  "h1" - (
    fontSize(28.px)
  )
  "h2" - (
    fontSize(24.px)
  )
  "h3" - (
    fontSize(20.px)
  )
  "h4" - (
    fontSize(18.px)
  )

  "table" - (
    borderSpacing(0.px),
    borderCollapse.collapse,
    borderWidth(1.px),
    borderStyle.solid,
  )
  "th" - (
    borderSpacing(0.px),
    borderCollapse.collapse,
    borderWidth(1.px),
    borderStyle.solid,
  )
  "td" - (
    borderSpacing(0.px),
    borderCollapse.collapse,
    borderWidth(1.px),
    borderStyle.solid,
  )
  "td" - (
    padding(7.px)
  )
  "blockquote" - (
    fontStyle.italic,
    marginLeft(15.px),
    padding(8.px)
  )
  "thead tr" - (
    backgroundColor := "#ebf6f7"
  )
  "a" - (
    textDecoration := "none",
    color := "#25c2a1"
  )
  "tbody tr:nth-child(even)" - (
    backgroundColor := "#ebf6f7"
  )
  "th" - (padding(7.px))
  "pre" - (
    borderRadius(6.px),
    padding(12.px),
    backgroundColor := "#212432",
    overflowX.scroll
  )
  "code" - (
    lineHeight(24.px),
    letterSpacing(0.1.em),
    color.whitesmoke,
    fontSize(1.em),
    borderStyle.none,
  )
  "code .keyword" - (
    color := "rgb(130, 170, 255)"
  )
  "code .escape-sequence" - (
    color := "rgb(130, 170, 255)"
  )
  "code .type-name" - (
    color := "rgb(255, 203, 107)"
  )
  "code .tag-name" - (
    color := "rgb(255, 203, 107)"
  )
  "code .declaration-name" - (
    color := "rgb(199, 146, 234)"
  )
  "code .attribute-name" - (
    color := "rgb(130, 170, 255)"
  )
  "code .identifier" - (
    color := "#bddcee"
  )
  "code .number-literal" - (
    color := "rgb(247, 140, 108)"
  )
  "code .string-literal" - (
    color := "rgb(195, 232, 141)"
  )
  "code .boolean-literal" - (
    color := "rgb(199, 146, 234)"
  )
  "code .markup-link-text" - (
    color := "#65A861"
  )
  "code .markup-link-target" - (
    color := "#c1d225"
  )
  "code .substitution" - (
    color := "#e28393"
  )
  "code .annotation" - (
    color := "rgb(247, 140, 108)"
  )
  "code .comment" - (
    color := "rgb(105, 112, 152)",
    fontStyle.italic
  )
  "code .markup-headline" - (
    color := "#bb88d2"
  )
  "code .markup-emphasized" - (
    color := "#d27b84"
  )
}
