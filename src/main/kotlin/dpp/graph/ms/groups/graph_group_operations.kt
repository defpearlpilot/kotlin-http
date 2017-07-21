package dpp.graph.ms.groups

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getOrElse
import dpp.graph.getHTTPHeader
import dpp.graph.ms.users.MSUser
import dpp.graph.ms.users.MSUserResponse
import dpp.graph.ms.users.NullMSUserResponse
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async


internal suspend fun getGroups(token: String): List<MSGroup>
{
  val httpHeader = getHTTPHeader(token)
  val URL = "https://graph.microsoft.com/v1.0/groups"

  val (_, _, result) = async(CommonPool) {
    URL.httpGet().header(*httpHeader).responseObject(MSGroupResponse.Deserializer())
  }.await()

  return result.getOrElse(NullMSGroupResponse).groups
}


internal suspend fun getGroupUsers(token: String, groupId: String): List<MSUser>
{
  val httpHeader = getHTTPHeader(token)
  val URL = "https://graph.microsoft.com/v1.0/groups/$groupId/members"

  val (_, _, result) = async(CommonPool) {
    URL.httpGet().header(*httpHeader).responseObject(MSUserResponse.Deserializer())
  }.await()

  return result.getOrElse(NullMSUserResponse).users
}