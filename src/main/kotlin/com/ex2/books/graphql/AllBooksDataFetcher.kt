package com.ex2.books.graphql

import com.ex2.books.models.Book
import com.ex2.books.repo.BooksRepo
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class AllBooksDataFetcher : DataFetcher<List<Book>> {

    @Autowired
    private lateinit var booksRepo: BooksRepo

    override fun get(environment: DataFetchingEnvironment?): List<Book> {
        return booksRepo.findAll(Sort.by(Sort.Order.asc("title")))
    }

}