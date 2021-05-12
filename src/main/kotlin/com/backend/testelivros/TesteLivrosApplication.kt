package com.backend.testelivros


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TesteLivrosApplication

fun main(args: Array<String>) {
    runApplication<TesteLivrosApplication>(*args)
    //WebScrape(). scrape()
}
