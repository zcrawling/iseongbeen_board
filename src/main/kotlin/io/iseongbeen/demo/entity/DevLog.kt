package io.iseongbeen.demo.entity

import jakarta.persistence.*

@Entity
@Table(name = "dev_logs")
class DevLog(
    @Id val id: String,
    var title: String,
    var summary: String,
    @Column(columnDefinition = "TEXT") var content: String,
    var date: String,
    @ElementCollection var tags: MutableList<String> = mutableListOf(),
    var pinned: Boolean = false,
    var readTime: Int
)
