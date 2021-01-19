package com.ex2.books.repo

import com.ex2.books.models.BookAuthorMapping
import org.springframework.data.jpa.repository.JpaRepository


interface BookAuthorRelation : JpaRepository<BookAuthorMapping, Void>