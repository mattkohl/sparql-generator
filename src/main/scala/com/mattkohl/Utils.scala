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

  def addRdfsLabel(variable: String, labelValue: String): String = s"""$variable rdfs:label "$labelValue""""

  def addOptionalRdfsLabel(variable: String, labelVariable: String): String = s"OPTIONAL { $variable rdfs:label $labelVariable } ."

  def extractSparql(triples: List[Triple]): List[String] = triples flatMap { triple =>
      val subjectVariable = labelToSparqlVariable(triple.state.s.label)
      val subjectLabelVariable = s"${subjectVariable}Label"
      if (triple.state.o.literal.nonEmpty) {
        val objectVariable = labelToSparqlVariable(triple.state.o.literal)

        List(
          s"""$subjectVariable rdf:type <${triple.state.s.uri}> . """,
          s"""$subjectVariable <${triple.state.p.uri}> $objectVariable . """,
          addRdfsLabel(objectVariable, triple.state.o.literal),
          addOptionalRdfsLabel(subjectVariable, subjectLabelVariable)
        )

      } else {
        val objectVariable = labelToSparqlVariable(triple.state.o.label)
        val objectLabelVariable = objectVariable + "Label"

        List(
          s"""$subjectVariable rdf:type <${triple.state.s.uri}> . """,
          s"""$objectVariable rdf:type <${triple.state.o.uri}> . """,
          s"""$subjectVariable <${triple.state.p.uri}> $objectVariable . """,
          addOptionalRdfsLabel(subjectVariable, subjectLabelVariable),
          addOptionalRdfsLabel(objectVariable, objectLabelVariable)
        )

      }
  }  
  
}
