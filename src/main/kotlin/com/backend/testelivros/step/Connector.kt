package com.backend.testelivros.step

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class Connector {

    fun getDocument(url: String): Document {
        return Jsoup.connect(url)
            .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36")
            .followRedirects(true)
            .get()
    }
}