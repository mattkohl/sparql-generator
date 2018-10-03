package com.mattkohl

import org.scalajs.dom.raw.Event
import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html.{className, div}

@react
class TriplePattern extends StatelessComponent {
  case class Props(index: Int, triple: Triple, ontologyId: Int, updateTriplePattern: Event => Unit, onEdit: Event => Unit, precedingComponents: List[TriplePattern])

  def renderSubject(): ReactElement = {
    div("subject")
  }

  def renderPredicate(): ReactElement = {
    div("predicate")
  }

  def renderObject(): ReactElement = {
    div("object")
  }

  def render(): ReactElement = {
    div(className :="triple-pattern row")(
      renderSubject(),
      renderPredicate(),
      renderObject()
    )
  }

}