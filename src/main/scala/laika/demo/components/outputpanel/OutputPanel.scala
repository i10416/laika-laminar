package laika.demo.components.outputpanel

import com.raquo.laminar.api.L._
import org.scalajs.dom
import scalacss.DevDefaults._
import laika.demo.OutputFormat
import laika.demo.components.menu._

object OutputPanel {

  def apply(rendered: EventStream[String], fmt: Var[OutputFormat])(implicit
      owner: Owner
  ): Node = {
    val unsafeHTML = div(
      className := OutputPanelStyle.outputArea.className.value
    ).amend(styleAttr:="overflow-y:scroll;")
    val layout = div(
      className := OutputPanelStyle.panel.className.value,
      h2(
        className := OutputPanelStyle.title.className.value,
        "Output"
      ),
      OutputMenu(
        Seq(
          OutputFormat.RenderedHTML,
          OutputFormat.HTMLSource,
          OutputFormat.ResolvedAST,
          OutputFormat.UnresolvedAST
        ),
        fmt
      ).amend(className := OutputPanelStyle.menu.className.value),
      div(
        className := OutputPanelStyle.wrap.className.value,
        unsafeHTML
      )
    )
    rendered
      .withCurrentValueOf(fmt)
      .addObserver(Observer.apply {
        case (str, _) =>
          unsafeHTML.ref.innerHTML = str
      })
    layout
  }
}
