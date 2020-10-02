package com.ex2.books.graphql.fetchers

import com.ex2.books.models.Book
import com.ex2.books.repo.BooksRepo
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class BookByIsbnFetcher : DataFetcher<Book> {

    @Autowired
    private lateinit var booksRepo: BooksRepo

    override fun get(environment: DataFetchingEnvironment?): Book {
        val isbn = environment?.arguments?.get("isbn")?.toString()
                ?: throw Exception("Argument isbn is missing")
        val book = booksRepo.findByIsbn(isbn).firstOrNull()
                ?: throw Exception("Book not found for $isbn")
        return book
    }

}