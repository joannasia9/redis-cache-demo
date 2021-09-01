package pl.redis.cache.demo.config

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import pl.redis.cache.demo.model.Album
import pl.redis.cache.demo.model.Song


@Configuration
open class RedisConfiguration {

    @Bean
    open fun redisCacheConfiguration(): RedisCacheConfiguration {
        val serializer = GenericJackson2JsonRedisSerializer(redisObjectMapper())

        return RedisCacheConfiguration
            .defaultCacheConfig()
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer))
    }

    private fun redisObjectMapper(): ObjectMapper {
        val polymorphicTypeValidator = BasicPolymorphicTypeValidator.builder()
            .allowIfSubType(Album::class.java)
            .allowIfSubType(Song::class.java)
            .allowIfSubType(List::class.java)
            .build()

        return ObjectMapper().apply {
            activateDefaultTyping(polymorphicTypeValidator, ObjectMapper.DefaultTyping.EVERYTHING, JsonTypeInfo.As.PROPERTY)
        }
    }
}