package com.backend.testelivros

import com.backend.testelivros.book.Book
import com.backend.testelivros.repository.BookRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var bookRepository: BookRepository

    @Test
    fun createBook() {
        val book = Book(
            title = "titulo",
            description = "descricao",
            isbn = "40028922"
        )


        val json = ObjectMapper().writeValueAsString(book)
        bookRepository.deleteAll()


        mockMvc.perform(
            MockMvcRequestBuilders.post("/books")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.title").value(book.title))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.description").value(book.description))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.isbn").value(book.isbn))
            .andDo(MockMvcResultHandlers.print())

    }


    @Test
    fun findAllItems() {
        val book = Book(
            title = "titulo",
            description = "descricao",
            isbn = "40028922"
        )
        bookRepository.save(book)

        mockMvc.perform(MockMvcRequestBuilders.get("/books/getAllItems"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("\$").isArray)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].id").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].title").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].description").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].isbn").isString)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun findByID() {
        val book = Book(
            title = "titulo",
            description = "descricao",
            isbn = "40028922"
        )
        bookRepository.save(book)

        mockMvc.perform(MockMvcRequestBuilders.get("/books/${book.id}"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.id").value(book.id))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.title").value(book.title))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.description").value(book.description))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.isbn").value(book.isbn))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getBookScrape() {

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andDo(MockMvcResultHandlers.print())

        Assertions.assertFalse(bookRepository.findAll().isEmpty())
    }


}