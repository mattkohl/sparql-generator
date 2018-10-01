package com.mattkohl

import slinky.core.{Component, StatelessComponent}
import slinky.core.annotations.react
import slinky.web.html._
import org.scalajs.dom.raw.{Event, HTMLInputElement}
import slinky.core.facade.ReactElement

import scala.scalajs.js.Date


case class SearchResult(text: String, id: Long)

@react
class SearchBar extends Component {
  type Props = Unit
  case class State(items: Seq[SearchResult], text: String)

  override def initialState = State(Seq.empty, "")

  def handleChange(e: Event): Unit = {
    val eventValue =
      e.target.asInstanceOf[HTMLInputElement].value
    setState(_.copy(text = eventValue))
  }

  def handleSubmit(e: Event): Unit = {
    e.preventDefault()

    if (state.text.nonEmpty) {
      val newItem = SearchResult(
        text = state.text,
        id = Date.now().toLong
      )

      setState(prevState => {
        State(
          items = prevState.items :+ newItem,
          text = ""
        )
      })
    }
  }

  override def render(): ReactElement = {
    div(
      h3("TODO"),
      SearchResultList(items = state.items),
      form(onSubmit := handleSubmit _)(
        input(
          onChange := handleChange _,
          value := state.text
        ),
        button(s"Add #${state.items.size + 1}")
      )
    )
  }
}

@react
class SearchResultList extends StatelessComponent {
  case class Props(items: Seq[SearchResult])

  override def render(): ReactElement = {
    ul(
      props.items.map { item =>
        li(key := item.id.toString)(item.text)
      }
    )
  }
}
