package dpp.graph.ms.users

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import dpp.graph.getHTTPHeader
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async


suspend fun getUsers(token: String): List<MSUser>
{
  val httpHeader = getHTTPHeader(token)
  val URL = "https://graph.microsoft.com/v1.0/users"

  val (_, _, result) = async(CommonPool) {
    URL.httpGet().header(*httpHeader).responseObject(MSUserResponse.Deserializer())
  }.await()

  return when (result) {
    is Result.Success -> {
      println("About to resolve ${result.value.value}")
      result.value.value
    }
    else -> listOf()
  }
}

