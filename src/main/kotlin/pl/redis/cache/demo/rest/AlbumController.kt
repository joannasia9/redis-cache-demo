package pl.redis.cache.demo.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.redis.cache.demo.service.AlbumService

@RestController
@RequestMapping("/albums")
class AlbumController(
    private val cachedAlbumService: AlbumService
) {
    @GetMapping("/{title}/songs")
    fun getSongs(@PathVariable title: String): ResponseEntity<List<String>> =
        ResponseEntity.ok(cachedAlbumService.getSongsByAlbumTitle(title))

    @GetMapping("/{title}/author")
    fun getAlbumByTitle(@PathVariable title: String): ResponseEntity<String> =
        ResponseEntity.ok(cachedAlbumService.getAuthorByAlbumTitle(title))
}