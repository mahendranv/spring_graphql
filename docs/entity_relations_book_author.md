# Nested entities 

## Many to Many relation

1. A book can have multiple authors
2. So does the authors

## SQL changes

Create a mapping table 

```sql
CREATE TABLE `link_book_author` (
  `book_id` int unsigned NOT NULL,
  `author_id` int unsigned NOT NULL,
  PRIMARY KEY (`book_id`,`author_id`)
)
```

This takes in book_id and author_id and creates a primary key combines both.


## Domain model changes in Book

In the book domain model mention where to look for authors and join query.

```kotlin
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
```

This tells the JPA that, there will be list of authors for the book. Look at `link_book_author` table for the mapping.
Use `book_id` to identify the rows and use `author_id` to expand the result into Author entity.  