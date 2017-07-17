package dpp.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
open class BookItApplication {
  companion object {
    @JvmStatic fun main(args: Array<String>) {
      SpringApplication.run(BookItApplication::class.java, *args)
    }
  }
}