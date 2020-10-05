package com.ex2.books.models

import javax.persistence.*

@Entity(name = "AUTHORS")
data class Author(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id: Int = -1,

        @Column(name = "NAME")
        val name: String = ""

)