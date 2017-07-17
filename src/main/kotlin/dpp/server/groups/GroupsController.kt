package dpp.server.groups

import dpp.graph.authentication.acquireToken
import dpp.graph.authentication.promiseToken
import dpp.graph.groups.MSGroup
import dpp.graph.users.MSUser
import dpp.graph.users.promiseGroupUsers
import dpp.graph.users.promiseGroups
import dpp.identity.test.TestIdentity
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.functional.bind
import nl.komponents.kovenant.then
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GroupsController
{
  @RequestMapping("/groups")
  fun getUsers(): List<MSGroup>
  {
    val identity = TestIdentity()
    return promiseToken(identity).bind { promiseGroups(it) }.get()
  }


  @RequestMapping("/groups/{groupName}")
  fun getUsers(@PathVariable("groupName") groupName: String): List<MSUser>
  {
    val identity = TestIdentity()
    val token = acquireToken(identity)

    return promiseGroups(token)
        .bind { groups ->
          val group = groups.find { g -> g.displayName == groupName }
          when (group)
          {
            null -> Promise.ofFail(Exception("Unable to find group"))
            else -> promiseGroupUsers(token, group.id)
          }
        }.get()
  }

}