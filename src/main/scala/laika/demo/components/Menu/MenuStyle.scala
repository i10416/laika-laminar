package laika.demo.components.menu

import scalacss.DevDefaults._

object MenuStyle extends StyleSheet.Inline {
  import dsl._

  val item = style(
    textAlign := "center",
    flex := "0 0 100px",
    fontSize := 0.8.em,
    border := "1px solid",
    color := "rgba(36,36,36,0.8)",
    borderRadius := 12.px,
    borderColor := "rgba(36,36,36,0.8)",
    padding := "4px 6px",
    cursor := "pointer",
    transition := "all 0.2s",
    &.hover {
      opacity := "0.8"
      boxShadow := "0 1px 2px 0 rgba(36,36,36,0.2)"
    }
  )

  val active = style(
    borderColor := "#25c2a0",
    color := "#25c2a0",
  )

  val menu = style(
    gap := "6px",
    display := "flex",
    justifyContent := "space-evenly",
    padding := "8px 4px",
    media.only.screen.maxWidth(968.px) {
      style(
        overflowX := "scroll",
        justifyContent := "start"
      )
    }
  )
  val menuTitle = style(
    textAlign := "center"
  )

  val toggleMenu = style(
    display := "flex",
    justifyContent := "start",
    padding := "8px 4px"
  )
  val toggle = style(
    display := "flex",
    flexDirection := "row",
    justifyContent := "center",
    cursor := "pointer",
    fontSize := 0.8.em
  )
  val left = style(
    borderRadius := "12px 0px 0px 12px",
    border := "1px solid",
    padding := "4px 6px"
  )
  val right = style(
    borderRadius := "0px 12px 12px 0px",
    border := "1px solid",
    padding := "4px 6px"
  )
}
