package dpp.graph

import dpp.graph.authentication.acquireToken
import dpp.graph.groups.MSGroup
import dpp.graph.groups.promiseGroups
import dpp.identity.Identity
import dpp.identity.test.TestIdentity
import nl.komponents.kovenant.functional.map


interface TokenProvider
{
  fun token(): String
}

class MSGraphGroupAPI(private val tokenProvider: TokenProvider)
{
  fun filter(predicate: (item: MSGroup)-> Boolean): MSGroup?
  {
    return promiseGroups(tokenProvider.token()).map {
      groups ->
      groups.find { predicate(it) }
    }.get()
  }

  fun named(name: String): MSGroup?
  {
    return filter { it.displayName == name }
  }

  fun identified(id: String) : MSGroup?
  {
    return filter { it.id == id }
  }
}


class MSGraphTokenProvider(private val identity: Identity): TokenProvider
{
  override fun token(): String
  {
    return acquireToken(identity)
  }
}

class MSGraphAPI(private val identity: Identity)
{
  val tokenProvider = MSGraphTokenProvider(identity)

  fun groups(): MSGraphGroupAPI {
    return MSGraphGroupAPI(tokenProvider)
  }

  fun group(id: String) {

  }

  fun users() {

  }

  fun user(id: String) {

  }
}


val api = MSGraphAPI(TestIdentity()).groups().named("Hi")