package dpp.graph.test

import dpp.graph.Graph
import dpp.graph.Groups
import dpp.graph.Users
import dpp.graph.test.groups.TestGroups


internal class TestGraph : Graph
{
  suspend override fun groups(): Groups
  {
    return TestGroups()
  }

  suspend override fun users(): Users
  {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}
