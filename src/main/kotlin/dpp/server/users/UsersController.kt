package dpp.server.users

import dpp.graph.authentication.TokenParameters
import dpp.graph.authentication.promiseToken
import dpp.graph.users.MSUser
import dpp.graph.users.promiseUsers
import dpp.identity.test.TestIdentity
import nl.komponents.kovenant.functional.bind
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController
{
  @RequestMapping("/users")
  fun getUsers(): List<MSUser>
  {
    val identity = TestIdentity()
    return promiseToken(identity).bind { promiseUsers(it) }.get()
  }
}