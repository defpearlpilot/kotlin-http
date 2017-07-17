package dpp.graph

fun getHTTPHeader(token: String): Array<Pair<String, String>>
{
  val auth = Pair("Authorization", "Bearer $token")
  val accept = Pair("Accept", "application/json")
  val contentType = Pair("Content-Type", "application/json")

  return arrayOf(auth, accept, contentType)
}
