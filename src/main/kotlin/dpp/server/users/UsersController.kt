package dpp.server.users

import dpp.graph.GraphAPIFactory
import dpp.graph.User
import dpp.identity.test.TestIdentity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController
{
  @RequestMapping("/users")
  suspend fun getUsers(): List<User>
  {
    return GraphAPIFactory.API(TestIdentity()).users().all()
  }
}