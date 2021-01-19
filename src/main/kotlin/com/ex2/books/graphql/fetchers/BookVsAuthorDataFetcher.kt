package com.ex2.books.graphql.fetchers

import com.ex2.books.models.BookAuthorMapping
import com.ex2.books.repo.BookAuthorRelation
import graphql.schema.DataFetcher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookVsAuthorDataFetcher {

    @Autowired
    private lateinit var repo: BookAuthorRelation

    private val mapAuthor = DataFetcher { env ->
        val list = env.arguments["bookVsAuthorList"]

        var result = emptyList<BookAuthorMapping>()
        if (list is ArrayList<*>) {
            result = repo.saveAll(
                    list.map { item ->
                        item as Map<*, *>
                        BookAuthorMapping(bookId = item["bookId"] as Int, authorId = item["authorId"] as Int)
                    }
            )
        }
        result
    }

    val mutations = mapOf(
            "mapAuthor" to mapAuthor
    )

}