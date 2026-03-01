package io.iseongbeen.demo.entity

import jakarta.persistence.*

@Entity
@Table(name = "projects")
class Project(
    @Id val id: String,
    var title: String,
    var description: String,
    var longDescription: String,
    @ElementCollection var stack: MutableList<String> = mutableListOf(),
    var status: String,
    var pinned: Boolean = false,
    var link: String? = null,
    var github: String? = null,
    var date: String,
    var category: String
)
