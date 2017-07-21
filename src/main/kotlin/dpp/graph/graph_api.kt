package dpp.graph

import java.time.LocalDateTime

interface Location
{
}

interface Collection<out T>
{
  suspend fun all(): List<T>
}


interface UniquelyIdentifiable<out T>
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


interface Meeting
{
  val owner: String
}

interface Meetings: Searchable<Meeting>
{

}

interface User
{
  val id: String
  val name: String
}


interface Users: UniquelyIdentifiable<User>, Collection<User>
{
}


interface Group
{
  val id: String
  val name: String

  suspend fun users(): Users
}


interface Groups: UniquelyIdentifiable<Group>, Collection<Group>
{
}


interface Graph
{
  suspend fun groups(): Groups

  suspend fun users(): Users
}
