package com.mattkohl

import slinky.core.Component
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html.div

case class TripleSubject(uri: String, label: String)
case class TriplePredicate(uri: String, label: String)
case class TripleObject(uri: String, label: String, literal: String)

@react
class Triple extends Component {
  type Props = Unit

  case class State(s: TripleSubject, p: TriplePredicate, o: TripleObject, complete: Boolean)

  override def initialState =
    State(
      TripleSubject("", ""),
      TriplePredicate("", ""),
      TripleObject("", "", ""),
      complete=false
    )

  def render(): ReactElement = {
    div("Triple")
  }
}



