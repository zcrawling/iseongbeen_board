package io.iseongbeen.demo.controller

import tools.jackson.databind.ObjectMapper
import org.springframework.web.bind.annotation.*
import java.io.File

@RestController
@RequestMapping("/api/about")
class AboutController(private val mapper: ObjectMapper) {

    private val file = File("./data/about.json")

    private fun read(): Map<*, *> {
        if (!file.exists()) return emptyMap<String, Any>()
        return mapper.readValue(file, Map::class.java)
    }

    @GetMapping
    fun get() = read()

    @PutMapping
    fun update(@RequestBody body: Map<String, Any>): Map<String, Any> {
        file.parentFile.mkdirs()
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, body)
        @Suppress("UNCHECKED_CAST")
        return body
    }
}
