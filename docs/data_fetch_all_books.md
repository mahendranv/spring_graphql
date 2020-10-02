# Fetch all Books

## GraphQL schema setup



```sql
schema {
    query: Query
}


## Query type
type Query {
    getAllBooks: [Book]
}

## GraphQL type definition
type Book {

    id: Int

    isbn: String

    title: String

    author: String

}
```



## Data fetcher for all Books

Data fetcher resolves incoming GraphQL query and fetches data frrom repository.

```
@Component
class AllBooksDataFetcher : DataFetcher<List<Book>> {

    @Autowired
    private lateinit var booksRepo: BooksRepo

    override fun get(environment: DataFetchingEnvironment?): List<Book> {
        return booksRepo.findAll(Sort.by(Sort.Order.asc("title")))
    }

}
```



## Registering data fetcher to graphQL service

Name `getAllBooks` resolves to the query type and instance of `AllBooksDataFetcher` is assigned for the same.



*GraphQLService.kt*

```
private fun buildWiring(): RuntimeWiring {
        return RuntimeWiring.newRuntimeWiring()
                .type(TypeRuntimeWiring.newTypeWiring("Query") { builder ->
                    builder
                            .dataFetcher("getAllBooks", allBooksDataFetcher)
                })
                .build()
    }
```

