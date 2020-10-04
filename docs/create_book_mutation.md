# Create Book mutation

## Define mutation in the schema

For mutation, a Mutation type has to be mentioned in schema block and input and output for the same. Like in query, mutation needs a npudatafetcher.

This doc covers the procedure starting from GraphQL to datafetcher and JPA.

## GraphQL

1. Defining input type

```
input BookInput {

    isbn: String

    title: String

    author: String
}
```

Note that instead type, input is used here. Only input type can be fed to the mutation/query as param.

2. Mutation type definition

```
type Mutation {

    createBook(block: BookInput): Int
}
```

This mutation takes in book input and returns the id integer generated in db.

3. Tell schema to use Mutation for mutation inputs

```
schema {
    query: Query,
    mutation: Mutation
}
```

## Data fetcher

Data fetcher uses the Books - JPA repository and executes the query and returns ID.

1. Data fetcher instance

BooksDataFetcher.kt

```kotlin
val createBook = DataFetcher { env ->
        val input = env.arguments["block"] as Map<String, Any>
        
        val block = Book(
                title = input["title"] as String,
                author = input["author"] as String,
                isbn = input["isbn"] as String,
                id = -1
        )
        booksRepo.save(block)
    }

val mutations = mapOf(
            "createBook" to createBook
    )

```

2. Register data fetcher instance to GraphQL

GraphQLService.kt
```kotlin
    type(TypeRuntimeWiring.newTypeWiring("Mutation") {
                    builder->
                    builder.dataFetchers(booksDataFetcher.mutations)
    })
```