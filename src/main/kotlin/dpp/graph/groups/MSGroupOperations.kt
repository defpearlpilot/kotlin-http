package dpp.graph.groups

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import dpp.graph.getHTTPHeader
import dpp.graph.groups.MSGroup
import dpp.graph.groups.MSGroupResponse
import dpp.graph.users.MSUser
import dpp.graph.users.MSUserResponse
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.deferred


fun promiseGroups(token: String): Promise<List<MSGroup>, Exception>
{
  val httpHeader = getHTTPHeader(token)
  val URL = "https://graph.microsoft.com/v1.0/groups"

  val deferredToken = deferred<List<MSGroup>, Exception>()
  URL.httpGet().header(*httpHeader).responseObject(MSGroupResponse.Deserializer(), {
    _, _, result ->
    when (result) {
      is Result.Success -> deferredToken.resolve(result.value.value)
      is Result.Failure -> deferredToken.reject(result.error)
    }
  })

  return deferredToken.promise
}


fun promiseGroupUsers(token: String, groupId: String): Promise<List<MSUser>, Exception>
{
  val httpHeader = getHTTPHeader(token)
  val URL = "https://graph.microsoft.com/v1.0/groups/$groupId/members"

  println(URL)
  val deferredToken = deferred<List<MSUser>, Exception>()

  URL.httpGet().header(*httpHeader).responseObject(MSUserResponse.Deserializer(), {
    _, _, result ->
    when (result) {
      is Result.Success -> deferredToken.resolve(result.value.value)
      is Result.Failure -> deferredToken.reject(result.error)
    }
  })

  return deferredToken.promise
}