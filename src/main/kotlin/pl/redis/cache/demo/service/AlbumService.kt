package pl.redis.cache.demo.service

import org.springframework.stereotype.Service
import pl.redis.cache.demo.repository.AlbumRepository

@Service
open class AlbumService(
    private val albumRepository: AlbumRepository
) {
    open fun getAuthorByAlbumTitle(title: String): String {
        print("Author retrieval run.\n")
        return albumRepository.getAlbumByTitle(title).author ?: error("Author for provided album name not found.")
    }

    open fun getSongsByAlbumTitle(title: String): List<String> {
        print("Songs retrieval run.\n")
        return albumRepository.getSongsByAlbumTitle(title).mapNotNull { it.title }
    }
}