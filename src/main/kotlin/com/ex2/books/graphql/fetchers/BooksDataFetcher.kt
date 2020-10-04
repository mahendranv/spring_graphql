package com.ex2.books.graphql.fetchers

import com.ex2.books.models.Book
import com.ex2.books.repo.BooksRepo
import graphql.schema.DataFetcher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BooksDataFetcher {

    @Autowired
    private lateinit var booksRepo: BooksRepo

    val createBook = DataFetcher { env ->
        val input = env.arguments["block"] as Map<String, Any>

        val block = Book(
                title = input["title"] as String,
                author = input["author"] as String,
                isbn = input["isbn"] as String,
                id = -1
        )
        booksRepo.save(block)
    }


    val mutations = mapOf(
            "createBook" to createBook
    )
}