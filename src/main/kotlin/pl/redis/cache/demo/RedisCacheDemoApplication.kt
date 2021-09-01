package pl.redis.cache.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
open class RedisCacheDemoApplication

fun main(args: Array<String>) {
    runApplication<RedisCacheDemoApplication>(*args)
}
