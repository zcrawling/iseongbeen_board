package io.iseongbeen.demo.repository
import io.iseongbeen.demo.entity.Project
import org.springframework.data.jpa.repository.JpaRepository
interface ProjectRepository : JpaRepository<Project, String>