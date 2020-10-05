package com.ex2.books.repo

import com.ex2.books.models.Author
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepo : JpaRepository<Author, Int>