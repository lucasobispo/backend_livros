package com.backend.testelivros.repository

import com.backend.testelivros.book.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {
}