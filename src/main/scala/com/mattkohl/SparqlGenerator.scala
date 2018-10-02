package com.mattkohl

import slinky.core.Component
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._

import scala.scalajs.js


@react
class SparqlGenerator extends Component {

  type Props = Unit // no props
  case class State(queryText: String, variables: List[String], queried: Boolean, results: List[String], sentences: List[String])

  override def initialState: State = State("", List(), queried=false, List(), List())

  def render: ReactElement = {
    div(className := "sparql-generator")(TripleComponent())
  }
}
