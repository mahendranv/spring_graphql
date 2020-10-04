package com.ex2.books.graphql.fetchers

import com.ex2.books.models.Book
import com.ex2.books.repo.BooksRepo
import graphql.schema.DataFetcher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class BooksDataFetcher {

    @Autowired
    private lateinit var booksRepo: BooksRepo

    ///////////////////////////////////////
    ////////   Mutations //////////////////
    ///////////////////////////////////////

    private val createBook = DataFetcher { env ->
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

    ///////////////////////////////////////
    ////////   Queries ////////////////////
    ///////////////////////////////////////

    private val getAllBooks = DataFetcher {
        booksRepo.findAll(Sort.by(Sort.Order.asc("title")))
    }

    private val bookByIsbn = DataFetcher { env ->
        val isbn = env?.arguments?.get("isbn")?.toString()
                ?: throw Exception("Argument isbn is missing")
        val book = booksRepo.findByIsbn(isbn).firstOrNull()
                ?: throw Exception("Book not found for $isbn")
        book
    }

    val queries = mapOf(
            "getAllBooks" to getAllBooks,
            "bookByIsbn" to bookByIsbn
    )

}