package dpp.graph.ms

import dpp.graph.Graph
import dpp.graph.Groups
import dpp.graph.TokenProvider
import dpp.graph.Users
import dpp.graph.ms.authentication.acquireToken
import dpp.graph.ms.groups.MicrosoftGroups
import dpp.graph.ms.users.MicrosoftUsers
import dpp.identity.Identity



class MSGraphTokenProvider(private val identity: Identity): TokenProvider
{
  override fun token(): String
  {
    return acquireToken(identity)
  }
}


internal class MicrosoftGraph(private val identity: Identity): Graph
{
  val tokenProvider = MSGraphTokenProvider(identity)

  internal fun tokenProvider(): TokenProvider
  {
    return tokenProvider
  }


  override suspend fun groups(): Groups
  {
    return MicrosoftGroups(this)
  }


  override suspend fun users(): Users
  {
    return MicrosoftUsers(this, null)
  }
}

