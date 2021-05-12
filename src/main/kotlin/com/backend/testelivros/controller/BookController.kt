package com.backend.testelivros.controller

import com.backend.testelivros.book.Book
import com.backend.testelivros.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController(private val service: BookService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody book: Book): Book = service.create(book)

    @GetMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun getScrapeBooks() {
        service.getScrapeBooks()
    }

    @GetMapping("/getAllItems")
    fun getAll(): List<Book> = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Book> =
        service.findById(id).map {
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) :ResponseEntity<Void> {
        service.deleteById(id)
        return ResponseEntity<Void>(HttpStatus.OK)
    }

}
