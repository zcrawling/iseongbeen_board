package io.iseongbeen.demo.repository
import io.iseongbeen.demo.entity.DevLog
import org.springframework.data.jpa.repository.JpaRepository
interface DevLogRepository : JpaRepository<DevLog, String>