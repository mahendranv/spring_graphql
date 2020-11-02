# Spring Boot with GraphQL 



## Intro

GraphQL is an alternative for traditional REST APIs. To put in simple words, **Through a single endpoint, you can access the required resources at the client end**, and communication is defined in terms of GraphQL mutations & queries. GraphQL is not related to db, it is a query language for APIs.



Refer https://graphql.org/ for the usage.



## Components

1. Spring Boot for server side application
2. MySQL to store data
3. GraphQL to expose the resources



## TODO

- [x] Project initial setup
- [x] Create & connectMySQL db
- [x] GraphQL basic setup
- [x] Data fetchers setup
- [x] GraphQL playground
- [x] Data fetcher - All items
- [x] Data fetcher - with query
- [x] GraphQL - mutation
- [x] GraphQL - Nested objects
- [ ] GraphQL - Graceful error handling
- [ ] Authentication/authorization
- [ ] OAuth


## Each setup on this project explained here
1. [Database and user authorization](./docs/db_setup.md)
2. [JPA Repository and entity](./docs/jpa_repository.md)
3. [GraphQL - Fetch all books](./docs/data_fetch_all_books.md)
4. [GraphQL - Book by ISBN](./docs/data_fetcher_book_by_isbn.md)
5. [GraphQL - Create book mutation](./docs/create_book_mutation.md)
5. [GraphQL - Nested entity handling](./docs/entity_relations_book_author.md)