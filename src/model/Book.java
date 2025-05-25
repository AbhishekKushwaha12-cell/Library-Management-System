package model;

public class Book {
    private String isbn;
    private String title;
    private String author;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    public String toFileString() {
        return isbn + "," + title + "," + author;
    }

    public static Book fromFileString(String data) {
        String[] parts = data.split(",");
        if (parts.length != 3) return null;
        return new Book(parts[0], parts[1], parts[2]);
    }
}
