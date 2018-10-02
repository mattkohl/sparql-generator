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
  case class Props(onSearchStringInput: Event => Unit)
  case class State(items: Seq[SearchResult], text: String)

  override def initialState = State(Seq.empty, "")

  def handleChange(e: Event): Unit = {
    val eventValue = e.target.asInstanceOf[HTMLInputElement].value
    setState(_.copy(text = eventValue))
    props.onSearchStringInput(e)
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
      form(className:= "search-bar", onSubmit := handleSubmit _)(
        input(
          onChange := handleChange _,
          value := state.text
        ),
        button(s"SUBMIT")
      )
  }
}

@react
class SearchResults extends StatelessComponent {
  case class Props(items: Seq[SearchResult])

  override def render(): ReactElement = {
    ul(
      props.items.map { item =>
        li(key := item.id.toString)(item.text)
      }
    )
  }
}

@react
class Search extends StatelessComponent {
  case class Props(items: Seq[SearchResult], onSearchStringInput: Event => Unit)

  override def render(): ReactElement = {
    div(className := "search")(
      SearchBar(onSearchStringInput=props.onSearchStringInput),
      SearchResults(items = props.items)
    )
  }
}