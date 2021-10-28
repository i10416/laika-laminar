package laika.demo.components.inputpanel

import org.scalajs.dom
import scalacss.DevDefaults._

object InputPanelStyle extends StyleSheet.Inline {
  import dsl._
  val a = 0
  val panel = style(
    padding := 12.px
  )
  val lineNumber = style(
    margin:="0",
    lineHeight(20.px),
    letterSpacing(0.1.em),
    color:="#fff"
  )

  val title = style(
    color := "rgba(24,24,24,0.3)",
    marginTop := "16px",
    fontSize := 1.7.em,
    marginBottom := "24px",
    paddingLeft := 12.px
  )
  val footer = style(
    textAlign := "right",
    padding := "4px 12px"
  )
  val wordCount = style(
    color := "#25c2a1",
    fontWeight := "bold",
    fontSize := 14.px
  )
  val inputBlock = style(
    padding := 18.px,
  display.flex,
  border := "none",
  lineHeight(20.px),
  margin:="0",
  backgroundColor := "#212432",
  borderRadius := 8.px,
  width := "-webkit-fill-available",
  )

  val inputarea = style(
    paddingLeft(8.px),
    borderWidth:="0",
    height := 65.vh,
    color := "#e8e6e3",
    border := "none",
    borderRadius := 8.px,
    letterSpacing(0.1.em),
    lineHeight(20.px),
    outline := "none",
    resize := "none",
    backgroundColor := "#212432",
    width := "-webkit-fill-available",
  )
}
