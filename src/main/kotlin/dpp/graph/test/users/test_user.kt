package dpp.graph.test.users

import dpp.graph.*


internal class TestUser(override val id: String, override val name: String) : User
{
  override fun toString(): String
  {
    return "User($id)[$name]"
  }
}

internal class TestUsers(private val users: List<User>) : Users
{
  suspend override fun all(): List<User>
  {
    return users
  }
}

