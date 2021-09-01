package pl.redis.cache.demo.model

data class Song(
    val title: String?
) {
    constructor() : this(null)
}