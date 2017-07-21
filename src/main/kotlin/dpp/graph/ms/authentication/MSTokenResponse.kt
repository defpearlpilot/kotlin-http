package dpp.graph.ms.authentication

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class MSTokenResponse(val access_token: String)
{
  class Deserializer : ResponseDeserializable<MSTokenResponse>
  {
    override fun deserialize(content: String) = Gson().fromJson(content, MSTokenResponse::class.java)
  }
}