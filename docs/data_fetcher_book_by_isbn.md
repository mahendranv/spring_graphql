# Fetch Book by ISBN

Refer basic setup in (Fetch All books setup)[./data_fetch_all_books.md]

## GraphQL query

```
type Query {
    bookByIsbn(isbn: String): Book
}
```

This defines the operation name, argument & return type.
 

## Repository changes

In BooksRepository, add `findByIsbn`. This will resolve to select query which looks for given argument in isbn field.

```

fun findByIsbn(isbn: String): List<Book>

```

## Data fetcher
Data fetcher invokes the repository's method and returns single item matches the isbn.

```
@Component
class BookByIsbnFetcher : DataFetcher<Book> {

    @Autowired
    private lateinit var booksRepo: BooksRepo

    override fun get(environment: DataFetchingEnvironment?): Book {
        // reads argument from environment and invokes repo
        val isbn = environment?.arguments?.get("isbn")?.toString()
                ?: throw Exception("Argument isbn is missing")
        val book = booksRepo.findByIsbn(isbn).firstOrNull()
                ?: throw Exception("Book not found for $isbn")
        return book
    }

}
```

## Service registration

Instance of `BookByIsbnFetcher` registered to graphQL service.

```
.dataFetcher("bookByIsbn", bookByIsbnFetcher)
```
