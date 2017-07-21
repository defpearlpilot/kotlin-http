@file:JvmName("Graph_apiKt")

package dpp.graph

import dpp.graph.ms.MicrosoftGraph
import dpp.graph.test.TestGraph
import dpp.identity.Identity


interface TokenProvider
{
  fun token(): String
}


fun <T: Any> expectOne(list: List<T>): T?
{
  return when
  {
    list.isEmpty() -> null
    list.size == 1 -> list.first()
    else -> throw Exception("Expected only one item in the list")
  }
}


class GraphAPIFactory
{
  companion object
  {
    fun API(identity: Identity): Graph
    {
      return MicrosoftGraph(identity)
    }

    fun testAPI(): Graph
    {
      return TestGraph()
    }
  }
}

