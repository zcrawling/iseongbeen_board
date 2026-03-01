package io.iseongbeen.demo.repository
import io.iseongbeen.demo.entity.Learning
import org.springframework.data.jpa.repository.JpaRepository
interface LearningRepository : JpaRepository<Learning, String>