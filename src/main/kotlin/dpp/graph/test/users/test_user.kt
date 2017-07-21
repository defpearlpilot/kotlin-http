package dpp.graph.ms.users

import dpp.graph.*
import dpp.graph.ms.MicrosoftGraph


internal class TestUser(override val id: String, override val name: String) : User
{
  override fun toString(): String
  {
    return "User($id)[$name]"
  }
}

internal class TestUsers : Users
{
  suspend override fun all(): List<TestUser>
  {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override suspend fun named(name: String): TestUser?
  {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override suspend fun identified(id: String): TestUser?
  {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}

