package dpp.graph.ms.users

import dpp.graph.*
import dpp.graph.ms.MicrosoftGraph


internal class MicrosoftUser(private val graph: MicrosoftGraph, private val user: MSUser) : User
{
  override val id: String
    get() = user.id
  override val name: String
    get() = user.displayName

  override fun toString(): String
  {
    return "User($id)[$name]"
  }
}


internal fun MSUser.toMicrosoftUser(graph: MicrosoftGraph) = MicrosoftUser(graph, this)


internal class MicrosoftUsers(private val graph: MicrosoftGraph, private val users: List<MicrosoftUser>?) : Users
{
  suspend override fun all(): List<User>
  {
    return when(users)
    {
      null -> {
        val msUsers = getUsers(graph.tokenProvider.token())
        msUsers.map { it.toMicrosoftUser(graph) }
      }
      else -> users
    }
  }

  override suspend fun named(name: String): User?
  {
    return all().find { it.name == name }
  }

  override suspend fun identified(id: String): User?
  {
    return all().find { it.id == id }
  }
}

