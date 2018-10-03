package com.mattkohl

import slinky.core.Component
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html.{className, div}
import Utils._

@react
class TriplePatterns extends Component {

  type Props = Unit
  case class State(triplePatterns: List[TriplePattern], currentIndex: Int, submitted: Boolean)

  override def initialState = State(List.empty[TriplePattern], 0, submitted=false)

  def renderTriplePatterns(): ReactElement = {
    state.triplePatterns map { tp =>
      tp.render()
    }
  }

  def render(): ReactElement = {
    div(
      div(className := "triple-patterns")("renderTriplePatterns()"),
      div(className := "buttons")(
        div(className := "add-button")("AddButton"),
        div(className := "submit-button")("SubmitButton"),
        div(className := "remove-button")("RemoveButton")
      )
    )
  }
}