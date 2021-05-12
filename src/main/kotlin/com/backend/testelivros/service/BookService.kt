package com.backend.testelivros.service

import com.backend.testelivros.book.Book
import java.util.*

interface BookService {
    fun create(book: Book): Book

    fun getScrapeBooks()

    fun getAll(): List<Book>

    fun findById(id: Long): Optional<Book>

    fun deleteById(id: Long)

}