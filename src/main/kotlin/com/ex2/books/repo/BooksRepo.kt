package com.ex2.books.repo

import com.ex2.books.models.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BooksRepo : JpaRepository<Book, Int> {

    fun findByIsbn(isbn: String): List<Book>

}