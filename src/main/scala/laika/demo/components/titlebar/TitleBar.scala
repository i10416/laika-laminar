package laika.demo.components.titlebar

import com.raquo.airstream.state.Var
import com.raquo.laminar.api.L._
import org.scalajs.dom
import scalacss.DevDefaults._



object TitleBar {
  def apply(title: String): Node = {
    div(
      className := TitleBarStyle.titleBar.className.value,
      h3(
        className := TitleBarStyle.titleText.className.value,
        title
      )
    )
  }
}
