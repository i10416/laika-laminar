package laika.demo.components.tab

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import scalacss.DevDefaults._
import laika.demo.components.titlebar.TitleBar



object Togglable {
  val hideLeft = Var(initial = false)
  def apply(left: Node, right: Node) = div(
    Row(hideLeft),
    div(
      className := TogglableStyle.togglable.className.value,
      div(
        cls.toggle(TogglableStyle.hide.className.value) <-- hideLeft.signal,
        className := TogglableStyle.togglableContent.className.value,
        left
      ),
      div(
        cls.toggle(TogglableStyle.hide.className.value) <-- hideLeft.signal.map(
          !_
        ),
        className := TogglableStyle.togglableContent.className.value,
        right
      )
    )
  )
}

object Row {
  val left = (label: String, hideLeft: Signal[Boolean]) =>
    div(
      cls.toggle(TogglableStyle.hiddenTabLabel.className.value) <-- hideLeft,
      className := TogglableStyle.rowItem.className.value,
      TitleBar(label)
    )
  val right = (label: String, hideLeft: Signal[Boolean]) =>
    div(
      cls.toggle(TogglableStyle.hiddenTabLabel.className.value) <-- hideLeft
        .map(!_),
      className := TogglableStyle.rowItem.className.value,
      TitleBar(label)
    )
  def apply(hideLeft: Var[Boolean]) = div(
    className := TogglableStyle.row.className.value,
    left("Edit", hideLeft.signal)
      .amendThis(thisNode =>
        thisNode.events(onClick).map(_ => false) --> hideLeft
      ),
    right("View", hideLeft.signal).amendThis(thisNode =>
      thisNode.events(onClick).map(_ => true) --> hideLeft
    )
  )
}
