package laika.demo.components.tab


import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import scalacss.DevDefaults._


object TogglableStyle extends StyleSheet.Inline {
  import dsl._
  val togglable = style(
    minHeight := 85.vh,
    display := "flex",
    justifyContent := "center",
    backgroundColor := "#f5f6f7",
    paddingBottom := 32.px
  )

  val togglableContent = style(
    width := 40.vw,
    media.only.screen
      .maxWidth(968.px)
      .apply(
        width := "100%"
      )
  )

  val row = style(
    display := "none",
    justifyContent := "center",
    media.only.screen
      .maxWidth(968.px)
      .apply(
        display := "flex"
      )
  )
  val divider = style(
    display := "none",
    margin := "0",
    media.only.screen
      .maxWidth(968.px)
      .apply(
        display := "block"
      )
  )
  val rowItem = style(
    padding := "4px 12px",
    cursor := "pointer"
  )
  val togglableItem = style(
    flexGrow := "1",
    flexShrink := "1",
    media.only.screen
      .maxWidth(968.px)
      .apply(
        flexGrow := "1",
        flexShrink := "0"
      )
  )
  val hide = style(
    media.only.screen
      .maxWidth(968.px)
      .apply(
        display := "none"
      )
  )
  val hiddenTabLabel = style(
    color := "rgba(70,70,70,0.6)"
  )
}

