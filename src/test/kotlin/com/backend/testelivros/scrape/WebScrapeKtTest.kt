package com.backend.testelivros.scrape


import com.backend.testelivros.step.Connector
import io.mockk.every
import io.mockk.mockk
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class WebScrapeKtTest {
    private val connector = mockk<Connector>()
    private val webScrape = WebScrape(connector)
    private val html = WebScrapeUtils.getFileContent("parser/kotlin.html")
    private val document = Jsoup.parse(html)

    @BeforeEach
    fun setUp() {
        every { connector.getDocument(any()) } returns document
    }

    @Test
    fun itemsScrape() {
        assertEquals(6, webScrape.scrape().size)
        assertTrue(webScrape.scrape()::isNotEmpty)
    }

    @Test
    fun replaceOrNotStringValue() {
        val isbn = "ISBN: 9781491996690"
        assertEquals("9781491996690", webScrape.replaceOrNot(isbn))
    }

    @Test
    fun replaceOrNotEmptyValue() {
        val isbn = ""
        assertEquals("Unavailable", webScrape.replaceOrNot(isbn))
    }

    @Test
    fun getDocument() {
        val html = WebScrapeUtils.getFileContent("parser/kotlin.html")
        assertEquals(document, webScrape.connector.getDocument(html))
        assertTrue(webScrape.connector.getDocument(html) is Document)
    }


}