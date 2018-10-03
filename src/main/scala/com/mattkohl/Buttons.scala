package com.mattkohl

import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._
import org.scalajs.dom.raw.Event

trait Buttons

@react
class SubmitButton extends StatelessComponent with Buttons {

  case class Props(render: Boolean, submit: Event => Unit)

  def render(): ReactElement = if (props.render) {
   a(className := "btn-floating btn-large waves-effect waves-light triple-pattern-submit", onClick := props.submit)(
    i(className := "material-icons")("get_app")
   )
  } else div()

}

@react
class AddButton extends StatelessComponent with Buttons {

  case class Props(render: Boolean, add: Event => Unit)

  def render(): ReactElement = if (props.render) {
    a(className := "btn-floating btn-large waves-effect waves-light triple-pattern-submit", onClick := props.add)(
      i(className := "material-icons")("add")
    )
  } else div()
}

@react
class RemoveButton extends StatelessComponent with Buttons {
  case class Props(render: Boolean, remove: Event => Unit)

  def render(): ReactElement = if (props.render) {
    a(className := "btn-floating btn-large waves-effect waves-light triple-pattern-submit", onClick := props.remove)(
      i(className := "material-icons")("remove")
    )
  } else div()
}
