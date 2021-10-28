package laika.demo.components.inputpanel

import com.raquo.airstream.state.Var
import com.raquo.laminar.api.L._
import laika.demo.components.menu.InputMenu
import laika.factory.MarkupFormat
import org.scalajs.dom
import scalacss.DevDefaults._

object InputPanel {
  def apply(userInput: Var[String], fmt: Var[MarkupFormat]): Node = {
    div(
      className := InputPanelStyle.panel.className.value,
      h2(
        className := InputPanelStyle.title.className.value,
        "Input"
      ),
      InputMenu(fmt),
      div(
        className := InputPanelStyle.inputBlock.className.value,
        textArea(
          autoFocus := true,
          className := InputPanelStyle.inputarea.className.value,
          onMountFocus,
          placeholder := """println("hello,world")""",
          inContext { node => onInput.map(_ => node.ref.value) --> userInput }
        )
      ),
      div(
        className := InputPanelStyle.footer.className.value,
        span(
          className := InputPanelStyle.wordCount.className.value,
          child.text <-- userInput.signal.map(_.length())
        ),
        span(
          " characters(800 max)"
        )
      )
    )
  }
}
