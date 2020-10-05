package com.ex2.books.graphql.fetchers

import com.ex2.books.models.Author
import com.ex2.books.repo.AuthorRepo
import graphql.schema.DataFetcher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class AuthorsDataFetcher {

    @Autowired
    private lateinit var repo: AuthorRepo

    private val getAllAuthors = DataFetcher {
        repo.findAll()
    }

    val queries = mapOf(
            "getAllAuthors" to getAllAuthors
    )

    private val createAuthor = DataFetcher { env ->
        val name = env.arguments["name"]
        if (name !is String) {
            throw Exception("Exception with argument name")
        }
        repo.save(Author(name = name))
    }

    val mutations = mapOf(
            "createAuthor" to createAuthor
    )
}