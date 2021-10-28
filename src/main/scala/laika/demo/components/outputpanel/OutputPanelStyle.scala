package laika.demo.components.outputpanel

import scalacss.DevDefaults._

object OutputPanelStyle extends StyleSheet.Inline {
  import dsl._
  val panel = style(
    padding := 12.px
  )
  val title = style(
    color := "rgba(24,24,24,0.3)",
    marginTop := "16px",
    fontSize := 1.7.em,
    paddingLeft := 12.px,
    marginBottom := "24px"
  )
  val footer = style(
    textAlign := "right",
    padding := "4px 12px"
  )
  val wordCount = style(
    color := "#25c2a0",
    fontWeight := "bold",
    fontSize := 14.px
  )
  val inputBlock = style(
  )

  val outputArea = style(
    paddingLeft(4.px),
    height := 65.vh,
    border := "none",
    borderRadius := 8.px,
    backgroundColor := "#fefefe",
    width := "-webkit-fill-available",
    padding := "18px 22px",
    color := "#41464d",
    boxShadow := "0 1px 50px 5px rgba(24,24,24,0.1)"
  )
  val wrap = style(
    position := "relative",
    overflowWrap := "break-word"
  )
  val menu = style(
  )

}
