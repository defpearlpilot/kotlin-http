package dpp.graph.test.groups

import dpp.graph.Group
import dpp.graph.Groups
import dpp.graph.Users
import dpp.graph.test.users.TestUser
import dpp.graph.test.users.TestUsers


class TestGroup(override val id: String, override val name: String, private val users: Users) : Group
{
  override fun toString(): String
  {
    return "Group($id)[$name]"
  }

  suspend override fun users(): Users = users
}


internal class TestGroups : Groups
{
  private val nycRooms = TestGroup("1", "nyc-rooms", TestUsers(listOf(TestUser("1", "Blue"))))

  override suspend fun all(): List<TestGroup> = listOf(nycRooms)
}

