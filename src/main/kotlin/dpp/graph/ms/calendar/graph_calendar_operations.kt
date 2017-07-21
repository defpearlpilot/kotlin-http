package dpp.graph.ms.calendar

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import dpp.graph.getHTTPHeader
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.deferred


fun promiseUserEvents(token: String, idOrName: String): Promise<List<MSEvent>, Exception>
{
  val httpHeader = getHTTPHeader(token)
  val URL = "https://graph.microsoft.com/v1.0/users/$idOrName@designitcontoso.onmicrosoft.com/events"
  println("Calling $URL")

  val deferredToken = deferred<List<MSEvent>, Exception>()

  URL.httpGet().header(*httpHeader).responseObject(MSEventResponse.Deserializer(), {
    request, response, result ->
    println(request)
    println(response)
    when (result) {
      is Result.Success -> deferredToken.resolve(result.value.value)
      is Result.Failure -> deferredToken.reject(result.error)
    }
  })

  return deferredToken.promise
}
