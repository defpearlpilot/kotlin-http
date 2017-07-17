package dpp.server.health

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController
{
  @RequestMapping("/ping")
  fun getHello(): String
  {
    return "Hello"
  }
}