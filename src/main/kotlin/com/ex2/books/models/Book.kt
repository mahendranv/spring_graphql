package com.ex2.books.models

import javax.persistence.*

@Entity(name = "books")
@Table(name = "books")
data class Book(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id: Int = -1,

        @Column(name = "title")
        val title: String = "",

        @Column(name = "isbn")
        val isbn: String? = null,

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(
                name = "link_book_author",
                joinColumns = [
                    JoinColumn(name = "book_id")
                ],
                inverseJoinColumns = [
                    JoinColumn(name = "author_id")
                ]
        )
        @Column(name = "authors")
        val authors: List<Author> = mutableListOf()
)