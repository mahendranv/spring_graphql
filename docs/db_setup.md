# My SQL Database setup

This section covers creating db/tables/users and setting up grants to the user.

### 1. Creating DB

Start MySql terminal by entering
```sql
sudo mysql 
```

Create db by running

```sql
create database books_db;
```

> Run `show databases;` to list databases 

Now, select the books db to create tables in it.

```sql
use books_db;
```


### 2. Tables

```sql
CREATE TABLE BOOKS (
    ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    TITLE VARCHAR(150) NOT NULL,
    AUTHOR VARCHAR(150) NOT NULL,
    ISBN VARCHAR(30),
    PRIMARY KEY(ID)
);
```

So far the db & table are ready. Now create a user in mysql and set access to the db.

### 3. Creating user

```sql

create user 'books_admin'@'localhost' identified by 'password@123';

```

Verify the list of users 

```sql
select user from mysql.user;
```

> Later if you want to change password
> Run `alter user 'books_admin'@'localhost' identified by 'newpassword@123';`

### 4. Setup access to the user

See current access grants for the user
```sql
show grants for 'book_admin'@'localhost';

+------------------------------------------------+
| Grants for book_admin@localhost                |
+------------------------------------------------+
| GRANT USAGE ON *.* TO `book_admin`@`localhost` |
+------------------------------------------------+
1 row in set (0.00 sec)
```

Now grant access to the book_admin

```sql
grant select, insert, update, delete, create, drop
on books_db.*
to 'book_admin'@'localhost';
```

Note that `books_db.*` means, `book_admin` will have access to all the tables in the db.

Again run the show grants command.

```sql

+------------------------------------------------------------------------------------------------+
| Grants for book_admin@localhost                                                                |
+------------------------------------------------------------------------------------------------+
| GRANT USAGE ON *.* TO `book_admin`@`localhost`                                                 |
| GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP ON `books_db`.* TO `book_admin`@`localhost` |
+------------------------------------------------------------------------------------------------+
2 rows in set (0.00 sec)
```

=== EOD ===