package com.ex2.books.models

import javax.persistence.*

@Entity(name = "books")
@Table(name = "books")
data class Book(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        val id: Int = -1,

        @Column(name = "title")
        val title: String = "",

        @Column(name = "isbn")
        val isbn: String? = null,

        @Column(name = "author")
        val author: String = ""

)