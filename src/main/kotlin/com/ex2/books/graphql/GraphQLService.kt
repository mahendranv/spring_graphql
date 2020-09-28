package com.ex2.books.graphql

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import java.net.URL
import graphql.GraphQL
import com.google.common.io.Resources
import graphql.schema.GraphQLSchema
import graphql.schema.idl.*
import org.springframework.beans.factory.annotation.Value
import java.io.File
import javax.annotation.PostConstruct
import javax.annotation.Resource

@Service
class GraphQLService {

    private lateinit var graphQL: GraphQL

    @Value("classpath:schema.graphqls")
    private lateinit var schemaFile: org.springframework.core.io.Resource

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
                .build()
    }

}