package io.iseongbeen.demo.controller

import io.iseongbeen.demo.entity.Project
import io.iseongbeen.demo.repository.ProjectRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/projects")
class ProjectController(private val repo: ProjectRepository) {

    @GetMapping
    fun getAll() = repo.findAll()

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: String) = repo.findById(id)

    @PostMapping
    fun create(@RequestBody project: Project) = repo.save(project)

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody project: Project) = repo.save(project)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) = repo.deleteById(id)
}
