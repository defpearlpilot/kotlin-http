package dpp.server.groups

import dpp.graph.GraphAPIFactory
import dpp.graph.Group
import dpp.identity.test.TestIdentity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GroupsController
{
  @RequestMapping("/groups")
  suspend fun getUsers(): List<Group>
  {
    return GraphAPIFactory.API(TestIdentity()).groups().all()
  }


  @RequestMapping("/groups/{groupName}")
  suspend fun getUsers(@PathVariable("groupName") groupName: String): Group?
  {
    return GraphAPIFactory.API(TestIdentity()).groups().named(groupName)
  }

}