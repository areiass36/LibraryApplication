DROP DATABASE IF EXISTS JavaFxDb;

CREATE DATABASE IF NOT EXISTS JavaFxDb;

DROP TABLE IF EXISTS JavaFxDb.User;

CREATE TABLE JavaFxDb.User (
    Id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(60) NOT NULL,
    Email VARCHAR(60) NOT NULL UNIQUE,
    Password VARCHAR(30) NOT NULL,
    Type TINYINT NOT NULL
);

DROP TABLE IF EXISTS JavaFxDb.Author;

CREATE TABLE JavaFxDb.Author (
    Id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(60) NOT NULL
);

DROP TABLE IF EXISTS JavaFxDb.Book;

CREATE TABLE JavaFxDb.Book (
    Id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(60) NOT NULL,
    Category TINYINT NOT NULL,
    IdAuthor BIGINT NOT NULL
);

DROP TABLE IF EXISTS JavaFxDb.BorrowedBooks;

CREATE TABLE JavaFxDb.BorrowedBooks (
    IdUser BIGINT NOT NULL,
    IdBook BIGINT NOT NULL UNIQUE,
    Date DATETIME NOT NULL,
    PRIMARY KEY (IdUser,IdBook),
    FOREIGN KEY (IdUser) REFERENCES JavaFxDb.User(Id),
    FOREIGN KEY (IdBook) REFERENCES JavaFxDb.Book(Id)
);

INSERT INTO JavaFxDb.User (Name,Email,Password,Type) VALUES ('Admin','admin','admin',1);
INSERT INTO JavaFxDb.User (Name,Email,Password,Type) VALUES ('Augusto','augusto.areias@fatec.sp.gov.br','pass',2);

INSERT INTO JavaFxDb.Author (Name) VALUES ('J.R.R. Token');
INSERT INTO JavaFxDb.Author (Name) VALUES ('J.K. Rowling');
INSERT INTO JavaFxDb.Author (Name) VALUES ('George Orwell');

INSERT INTO JavaFxDb.Book (Title, Category,IdAuthor) VALUES ('The Hobbit',2,1);
INSERT INTO JavaFxDb.Book (Title, Category,IdAuthor) VALUES ('The Silmarillion',1,1);
INSERT INTO JavaFxDb.Book (Title, Category,IdAuthor) VALUES ('Lord Of The Rings I',1,1);
INSERT INTO JavaFxDb.Book (Title, Category,IdAuthor) VALUES ('Lord Of The Rings II',1,1);
INSERT INTO JavaFxDb.Book (Title, Category,IdAuthor) VALUES ('Lord Of The Rings III',1,1);
INSERT INTO JavaFxDb.Book (Title, Category,IdAuthor) VALUES ('Harry Potter - Sorcerer\'s stone',2,2);
INSERT INTO JavaFxDb.Book (Title, Category,IdAuthor) VALUES ('Harry Potter - Chamber of secrets',2,2);
INSERT INTO JavaFxDb.Book (Title, Category,IdAuthor) VALUES ('Harry Potter - Order of the phoenix',2,2);
INSERT INTO JavaFxDb.Book (Title, Category,IdAuthor) VALUES ('1984',3,3);