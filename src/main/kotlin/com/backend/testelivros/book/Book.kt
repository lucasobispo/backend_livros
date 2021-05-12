package com.backend.testelivros.book

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "books")

data class Book(
    @Id @GeneratedValue

    var id: Long? = null,
    val title: String,
    @Column(length = 2048)
    val description: String,
    val isbn: String
)