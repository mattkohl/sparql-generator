package com.mattkohl

import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, ScalaJSDefined}

@JSImport("resources/App.css", JSImport.Default)
@js.native
object AppCSS extends js.Object

@JSImport("resources/logo.svg", JSImport.Default)
@js.native
object ReactLogo extends js.Object

@react
class App extends StatelessComponent {
  type Props = Unit

  private val css = AppCSS

  def render(): ReactElement = {
    div(className := "App")(
      header(className := "App-header")(
        h2(className := "App-title-h2")(
          span(className := "App-title-left")("SPARQL"),
          img(src := ReactLogo.asInstanceOf[String], className := "App-logo", alt := "logo"),
          span(className := "App-title-right")("Generator"),
        ),
      ),
      div(className := "App-body")(
        div(className := "container")(
          SparqlGenerator()
        )
      )
    )
  }
}
