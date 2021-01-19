package com.ex2.books.models

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity(name = "link_book_author")
@IdClass(BookAuthorMapping::class)
data class BookAuthorMapping(

        @Id
        @Column(name = "book_id")
        val bookId: Int,

        @Id
        @Column(name = "author_id")
        val authorId: Int) : Serializable
