package dpp.graph.ms.authentication


import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import dpp.identity.Identity
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.deferred
import nl.komponents.kovenant.functional.map


fun promiseToken(identity: Identity): Promise<String, Exception>
{
  return postToken(identity).map { token -> token.access_token }
}


fun acquireToken(identity: Identity): String
{
  return promiseToken(identity).get()
}


private fun postToken(identity: Identity) : Promise<MSTokenResponse, Exception>
{
  val idParams = identityToParameters(identity)
  val parameters = idParams.plus(Pair("grant_type", "client_credentials")).plus(Pair("resource", "https://graph.microsoft.com"))

  val URL = "https://login.windows.net/${identity.tenantId()}/oauth2/token"

  val deferredToken = deferred<MSTokenResponse, Exception>()
  URL.httpPost(parameters).responseObject(MSTokenResponse.Deserializer(), {
    _, _, result ->
    when (result) {
      is Result.Success -> deferredToken.resolve(result.value)
      is Result.Failure -> deferredToken.reject(Exception("Unable to obtain a token"))
    }
  })

  return deferredToken.promise
}


private fun identityToParameters(identity: Identity): List<Pair<String, String>>
{
  val clientId = Pair("client_id", identity.clientId())
  val clientSecret = Pair("client_secret", identity.secret())

  return listOf(clientId, clientSecret)
}


