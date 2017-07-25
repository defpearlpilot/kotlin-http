package dpp.graph

import dpp.graph.test.groups.TestGroup
import java.time.LocalDateTime

interface Location
{
}

interface Collection<out T>
{
  suspend fun all(): List<T>
}


interface Identifiable
{
  val name: String
  val id: String
}


interface UniquelyIdentifiable<out T:Identifiable>
{
  suspend fun named(name: String): T?
  suspend fun identified(id: String) : T?
}


interface Searchable<T>
{
  suspend fun between(start: LocalDateTime, end: LocalDateTime): List<T>
  suspend fun ownedBy(name: String): List<T>
  suspend fun inLocation(location: Location)
}


interface IdentifiableCollection<out T:Identifiable>: Collection<T>, UniquelyIdentifiable<T>
{
  // all "mixed-in" from actual implementing class
  private suspend fun find(predicate: (item: T) -> Boolean): T? = all().find { predicate(it) }

  override suspend fun named(name: String): T? = find { it.name == name }
  override suspend fun identified(id: String): T? = find { it.id == id }
}

interface Meeting
{
  val owner: String
}

interface Meetings: Searchable<Meeting>
{

}

interface User: Identifiable
{
}


interface Users: IdentifiableCollection<User>
{
}


interface Group: Identifiable
{

  suspend fun users(): Users
}


interface Groups: IdentifiableCollection<Group>
{
}


interface Graph
{
  suspend fun groups(): Groups
  suspend fun users(): Users
}
