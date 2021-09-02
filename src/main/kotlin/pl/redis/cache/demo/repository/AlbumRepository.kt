package pl.redis.cache.demo.repository

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import pl.redis.cache.demo.model.Album
import pl.redis.cache.demo.model.Song

@Component
open class AlbumRepository {
    private val albums = listOf(
        Album("Queen", "A Night At The Opera", listOf(
            Song("Lazing on a Sunday"),
            Song("You're My Best Friend"),
            Song("Sweet Lady"),
            Song("Bohemian Rhapsody")
        )),
        Album("Dzem", "Cegla", listOf(
            Song("Czerwony jak cegla"),
            Song("Whisky")
        )),
        Album("Nothing But Thieves", "Broken Machine", listOf(
            Song("I Was Just a Kid"),
            Song("Amsterdam"),
            Song("Sorry"),
            Song("Soda")
        )),
    )

    @Cacheable(cacheNames = ["albumsCache"])
    open fun getAlbumByTitle(title: String): Album {
        print("getAlbumByTitle repository call.\n")
        return albums.first { it.title?.trim()?.lowercase() == title.trim().lowercase() }
    }

    @Cacheable(cacheNames = ["songsCache"], key = "#title.toUpperCase()")
    open fun getSongsByAlbumTitle(title: String): List<Song> {
        print("getSongsByAlbumTitle repository call.\n")
        return albums.first { it.title?.trim()?.lowercase() == title.trim().lowercase() }.songs
            ?: error("No songs found for provided album.")
    }
}
