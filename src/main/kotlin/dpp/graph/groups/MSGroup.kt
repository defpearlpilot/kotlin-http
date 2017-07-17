package dpp.graph.groups

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson


data class MSGroup(val id: String,
                   val classification: String?,
                   val description: String,
                   val displayName: String,
                   val groupTypes: List<String>,
                   val mail: String?,
                   val mailEnabled: Boolean,
                   val mailNickname: String,
//                   val onPremisesLastSyncDateTime: LocalDateTime?,
                   val onPremisesProvisioningErrors: List<String>,
                   val onPremisesSecurityIdentifier: String?,
                   val onPremisesSyncEnabled: Boolean?,
                   val proxyAddresses: List<String>,
//                   val createdDateTime: LocalDateTime?,
//                   val deletedDateTime: LocalDateTime?,
//                   val renewedDateTime: LocalDateTime?,
                   val securityEnabled: Boolean,
                   val visibility: String
                  )
{
  class Deserializer : ResponseDeserializable<MSGroup>
  {
    override fun deserialize(content: String) = Gson().fromJson(content, MSGroup::class.java)
  }

}


class MSGroupResponse(val value: List<MSGroup>)
{
  class Deserializer : ResponseDeserializable<MSGroupResponse>
  {
    override fun deserialize(content: String) = Gson().fromJson(content, MSGroupResponse::class.java)
  }

}
