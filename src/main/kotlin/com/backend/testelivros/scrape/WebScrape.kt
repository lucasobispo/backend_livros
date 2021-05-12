package com.backend.testelivros.scrape

import com.backend.testelivros.book.Book
import com.backend.testelivros.step.Connector
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element


class WebScrape(
    val connector: Connector
) {
    //val connector = Connector()
    fun scrape(): List<Book> {
        val oreilly = "div.t-isbn"
        val manning = "div.product-meta li:nth-child(2)"
        val amazon = "#isbn_feature_div div div:nth-child(1) span:nth-child(2)"
        val pragprog = "div.book-about.book-about-text p:nth-child(2)"
        val url = "https://kotlinlang.org/docs/books.html"

        val bookList: MutableList<Book> = ArrayList()

        try {
            val document = connector.getDocument(url)
            for (row: Element in document.select(
                "table.no_header.wide tr"
            )) {
                val link = row.select("td:last-child p:first-child a").attr("href")
                val documentLink: Document =
                    if (link.contains("https://pragprog.com/book/vskotlin/programming-kotlin")) {
                        val documentRedirectLink = connector.getDocument(link)
                        val hrefNewLink = documentRedirectLink.select("link").attr("href")
                        val newLink = "https://pragprog.com/$hrefNewLink"
                        connector.getDocument(newLink)
                    } else {
                        connector.getDocument(link)
                    }

                val querySelector =
                    documentLink.select("$oreilly,$manning,$amazon,$pragprog")
                val isbn = if (querySelector.text().contains("Pages:", ignoreCase = true)) {
                    querySelector.text()
                        .replace("Pages: 460 Published: September 2019 ISBN:" to "", " Edition: 1 In Print" to "")
                } else {
                    querySelector.text()
                }
                bookList.add(
                    Book(
                        title = row.select("img").attr("title"),
                        description = row.select("td:last-child p:nth-child(2)").text(),
                        isbn = replaceOrNot(isbn) //isbnCheck

                    )
                )
            }
            return bookList
        } catch (e: Exception) {
            throw e
        }
    }

    fun String.replace(vararg replacements: Pair<String, String>): String {
        var result = this
        replacements.forEach { (l, r) -> result = result.replace(l, r) }
        return result
    }

    fun replaceOrNot(isbn: String): String {
        val re = Regex("[^0-9]")

        return if (re.replace(isbn, "").isNullOrEmpty()) {
            "Unavailable"
        } else {
            re.replace(isbn, "")
        }

    }
}

