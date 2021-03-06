package dpp.graph.ms.users

import dpp.graph.*
import dpp.graph.ms.MicrosoftGraph


internal class MicrosoftUser(private val graph: MicrosoftGraph, private val user: MSUser) : User
{
  override val id: String
    get() = user.id

  override val name: String
    get() = user.displayName


  override fun toString(): String = "User($id)[$name]"
}


internal fun MSUser.toMicrosoftUser(graph: MicrosoftGraph) = MicrosoftUser(graph, this)


internal class MicrosoftUsers(private val graph: MicrosoftGraph, private val users: List<MicrosoftUser>?) : Users
{
  suspend override fun all(): List<MicrosoftUser>
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
}

