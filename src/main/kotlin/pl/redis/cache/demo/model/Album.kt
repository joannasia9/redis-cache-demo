package pl.redis.cache.demo.model

data class Album(
    val author: String?,
    val title: String?,
    val songs: List<Song>?
) {
    constructor() : this(null, null, null)
}