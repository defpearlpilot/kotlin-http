package dpp.graph.ms.groups

import dpp.graph.Group
import dpp.graph.Groups
import dpp.graph.User
import dpp.graph.Users
import dpp.graph.ms.MicrosoftGraph
import dpp.graph.ms.users.MicrosoftUsers
import dpp.graph.ms.users.toMicrosoftUser


internal class U : Users {
  suspend override fun all(): List<User>
  {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  suspend override fun named(name: String): User?
  {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  suspend override fun identified(id: String): User?
  {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}


internal class MicrosoftGroup(private val graph: MicrosoftGraph, private val group: MSGroup) : Group
{
  override val name: String
    get() = group.displayName

  override val id: String
    get() = group.id

  override fun toString(): String
  {
    return "Group($id)[$name]"
  }

  suspend override fun users(): Users
  {
    val msUsers = getGroupUsers(graph.tokenProvider().token(), id).map { it.toMicrosoftUser(graph) }
    return MicrosoftUsers(graph, msUsers)
  }
}


internal fun MSGroup.toMicrosoftGroup(graph: MicrosoftGraph) = MicrosoftGroup(graph, this)


internal class MicrosoftGroups(private val graph: MicrosoftGraph) : Groups
{
  override suspend fun all(): List<Group>
  {
    val msGroups = getGroups(graph.tokenProvider().token())
    return msGroups.map {  it.toMicrosoftGroup(graph) }
  }

  override suspend fun named(name: String): Group?
  {
    return find { it.displayName == name }
  }

  override suspend fun identified(id: String): Group?
  {
    return find { it.id == id }
  }

  private suspend fun find(predicate: (item: MSGroup) -> Boolean): Group?
  {
    return getGroups(graph.tokenProvider().token()).find { predicate(it) }?.toMicrosoftGroup(graph)
  }
}

