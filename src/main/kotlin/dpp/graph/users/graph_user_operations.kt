package dpp.graph.users

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import dpp.graph.getHTTPHeader
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.deferred


fun getUsers(token: String): Promise<List<MSUser>, Exception>
{
  val httpHeader = getHTTPHeader(token)
  val URL = "https://graph.microsoft.com/v1.0/users"

  val deferredToken = deferred<List<MSUser>, Exception>()

  URL.httpGet().header(*httpHeader).responseObject(MSUserResponse.Deserializer(), {
    _, _, result ->
    when (result) {
      is Result.Success -> deferredToken.resolve(result.value.value)
      is Result.Failure -> deferredToken.reject(Exception("Unable to obtain a token"))
    }
  })

  return deferredToken.promise
}


fun promiseUsers(token: String): Promise<List<MSUser>, Exception>
{
  return getUsers(token)
}
