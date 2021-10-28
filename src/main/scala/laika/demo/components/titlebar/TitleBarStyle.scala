package laika.demo.components.titlebar


import com.raquo.airstream.state.Var
import com.raquo.laminar.api.L._
import org.scalajs.dom
import scalacss.DevDefaults._

object TitleBarStyle extends StyleSheet.Inline {
  import dsl._

  val titleText = style(
    margin := "0"
  )
  val titleBar = style(
    textAlign := "center"
  )
}
