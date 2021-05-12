package com.backend.testelivros.scrape

import java.io.File
import java.nio.file.Files

object WebScrapeUtils {
    fun getFileContent(fileName: String): String {
        val url = this::class.java.classLoader.getResource(fileName)!!
        val file = File(url.toURI())
        return Files.readAllLines(file.toPath()).reduce { acc, s -> acc + s }
    }

}