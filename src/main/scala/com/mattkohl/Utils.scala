package com.mattkohl

object Utils {

  def extractVariables(triples: List[Triple]): List[String] = {
    triples flatMap { triple =>
      val sVar = labelToSparqlVariable(triple.state.s.label)
      val sLabelVar = labelToSparqlVariable(triple.state.s.label + "Label")
      val oVar = labelToSparqlVariable(triple.state.o.label)
      val oLabelVar = labelToSparqlVariable(triple.state.o.label + "Label")
      val oLitVar = labelToSparqlVariable(triple.state.o.literal)
      Set(sVar, sLabelVar, oVar, oLabelVar, oLitVar).filter(_ != "?Label")
    }
  }

  def labelToVariable(label: String): String = label.replace(" ", "-").replace(".", " ")
  def labelToSparqlVariable(label: String): String = s"?${labelToVariable(label)}"


  def addRdfsLabel(variable: String, labelValue: String): String = { variable + " rdfs:label \"" + labelValue + "\" . " }
  def addOptionalRdfsLabel(variable: String, labelVariable: String): String = { "OPTIONAL { " + variable + " rdfs:label " + labelVariable + " } . " }

}
