REATE DATABASE IF NOT EXISTS library;

USE library;

CREATE TABLE Book (
    isbn VARCHAR(20) PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255)
);
