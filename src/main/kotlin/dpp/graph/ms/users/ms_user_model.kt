package dpp.graph.ms.users

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson


data class MSUser(val id: String,
                  val userPrincipalName: String,
                  val displayName: String,
                  val givenName: String,
                  val surname: String,
                  val mail: String,
                  val mobilePhone: String,
                  val businessPhones: List<String>,
                  val officeLocation: String,
                  val preferredLanguage: String)
{
  class Deserializer : ResponseDeserializable<MSUser>
  {
    override fun deserialize(content: String) = Gson().fromJson(content, MSUser::class.java)
  }

}


class MSUserResponse(val value: List<MSUser>)
{
  val users: List<MSUser>
    get() = value

  class Deserializer : ResponseDeserializable<MSUserResponse>
  {
    override fun deserialize(content: String) = Gson().fromJson(content, MSUserResponse::class.java)
  }

}

val NullMSUserResponse = MSUserResponse(listOf())