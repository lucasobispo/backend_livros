package com.backend.testelivros.service

import com.backend.testelivros.book.Book
import com.backend.testelivros.repository.BookRepository
import com.backend.testelivros.scrape.WebScrape
import com.backend.testelivros.step.Connector
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookServiceImpl(private val repository: BookRepository) : BookService {
    override fun create(book: Book): Book {
        return repository.save(book)

    }

    override fun getScrapeBooks() {
        WebScrape(Connector()).scrape().forEach { repository.save(it) }
    }

    override fun getAll(): List<Book> {
        return repository.findAll()
    }

    override fun findById(id: Long): Optional<Book> {
        return repository.findById(id)
    }

    override fun deleteById(id: Long) {
        repository.findById(id).map {
            repository.delete(it)
        }.orElseThrow { throw RuntimeException("Id not found") }
    }


}