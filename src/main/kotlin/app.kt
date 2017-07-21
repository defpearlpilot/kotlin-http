import dpp.graph.GraphAPIFactory
import dpp.identity.test.TestIdentity
import kotlinx.coroutines.experimental.runBlocking

class App
{
  companion object
  {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking<Unit>
    {
      val API = GraphAPIFactory.API(TestIdentity())
      API.groups().named("nyc-rooms")?.users()?.all()?.forEach { println(it) }
    }
  }
}