package dpp.server.groups

import dpp.graph.authentication.acquireToken
import dpp.graph.calendar.promiseUserEvents
import dpp.graph.groups.MSEvent
import dpp.identity.test.TestIdentity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MeetingsController
{
  @RequestMapping("/meetings/{userName}")
  fun getUsers(@PathVariable("userName") userName: String): List<MSEvent>
  {
    val identity = TestIdentity()
    val token = acquireToken(identity)
    return promiseUserEvents(token, userName).get()
  }

}