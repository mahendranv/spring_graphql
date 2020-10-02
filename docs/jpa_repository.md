# JPA repository setup

This setup is not specific to GraphQL. Any other serializer can make use of this.



## Book Entity

Entity is a POJO class that maps field against column name. A basic entity should annotate @Id column. This will help Repository to fetch single row from table by Id.

```kt
import javax.persistence.*

@Entity(name = "books")
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
```


## JPA Repository

A basic repository requires a single line of code which mentions Entity and Id in generic class.

```kt

interface BooksRepo : JpaRepository<Book, Int>

```


JpaRepository has built in methods for CRUD operations on the table.
