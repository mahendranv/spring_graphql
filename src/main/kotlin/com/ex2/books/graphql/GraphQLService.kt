package com.ex2.books.graphql

import com.ex2.books.graphql.fetchers.AllBooksDataFetcher
import com.ex2.books.graphql.fetchers.BookByIsbnFetcher
import com.ex2.books.graphql.fetchers.BooksDataFetcher
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import graphql.GraphQL
import graphql.schema.GraphQLSchema
import graphql.schema.idl.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import java.io.File
import javax.annotation.PostConstruct

@Service
class GraphQLService {

    private lateinit var graphQL: GraphQL

    @Value("classpath:schema.graphqls")
    private lateinit var schemaFile: org.springframework.core.io.Resource

    @Autowired
    private lateinit var allBooksDataFetcher: AllBooksDataFetcher

    @Autowired
    private lateinit var bookByIsbnFetcher: BookByIsbnFetcher

    @Autowired
    private lateinit var booksDataFetcher: BooksDataFetcher

    @Bean
    fun graphQL(): GraphQL {
        return graphQL
    }

    @PostConstruct
    private fun init() {
        val graphQLSchema: GraphQLSchema = buildSchema(schemaFile.file)
        graphQL = GraphQL.newGraphQL(graphQLSchema).build()
    }

    private fun buildSchema(sdl: File): GraphQLSchema {
        val typeRegistry: TypeDefinitionRegistry = SchemaParser().parse(sdl)
        val runtimeWiring: RuntimeWiring = buildWiring()
        val schemaGenerator = SchemaGenerator()
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring)
    }

    private fun buildWiring(): RuntimeWiring {
        return RuntimeWiring.newRuntimeWiring()
                .type(TypeRuntimeWiring.newTypeWiring("Query") { builder ->
                    builder
                            .dataFetcher("getAllBooks", allBooksDataFetcher)
                            .dataFetcher("bookByIsbn", bookByIsbnFetcher)
                })
                .type(TypeRuntimeWiring.newTypeWiring("Mutation") {
                    builder->
                    builder.dataFetchers(booksDataFetcher.mutations)
                })
                .build()
    }

}