package io.iseongbeen.demo.entity

import jakarta.persistence.*

@Entity
@Table(name = "learning_entries")
class Learning(
    @Id val id: String,
    var title: String,
    var topic: String,
    var summary: String,
    @Column(columnDefinition = "TEXT") var content: String,
    var date: String,
    @ElementCollection var tags: MutableList<String> = mutableListOf(),
    var pinned: Boolean = false,
    var source: String? = null,
    var progress: Int = 0
)
