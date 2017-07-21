package dpp.graph.test.groups

import dpp.graph.Group
import dpp.graph.Groups
import dpp.graph.Users


class TestGroup(override val name: String, override val id: String) : Group
{
  override fun toString(): String
  {
    return "Group($id)[$name]"
  }

  suspend override fun users(): Users
  {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}


internal class TestGroups : Groups
{
  override suspend fun all(): List<TestGroup>
  {
    return listOf(TestGroup("test", "1"))
  }

  override suspend fun named(name: String): TestGroup?
  {
    return find { it.name == name }
  }

  override suspend fun identified(id: String): TestGroup?
  {
    return find { it.id == id }
  }

  private suspend fun find(predicate: (item: TestGroup) -> Boolean): TestGroup?
  {
    return all().find { predicate(it) }
  }
}

