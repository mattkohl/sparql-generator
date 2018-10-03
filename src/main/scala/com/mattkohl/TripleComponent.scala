package com.mattkohl

import slinky.core.Component
import slinky.core.annotations.react
import slinky.web.html._
import org.scalajs.dom.raw.{Event, HTMLInputElement}
import slinky.core.facade.ReactElement


@react
class TripleComponent extends Component {
  type Props = Unit
  case class State(items: Seq[SearchResult], text: String)

  override def initialState = State(Seq.empty, "")

  val handleSearchStringInput: Event => Unit = (e: Event) => {
    val eventValue = e.target.asInstanceOf[HTMLInputElement].value
    setState(_.copy(text = eventValue))
  }

  def render(): ReactElement = {
    div(className := "triple-component col s10 offset-s1 m10 offset-m1 l4")(
      Search(items=Seq(), onSearchStringInput=handleSearchStringInput)
    )
  }
}


