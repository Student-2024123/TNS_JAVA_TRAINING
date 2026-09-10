package Assignment11;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private String publication;
    private int year;
    private int copiesAvailable;

    public Book() {
    }

    public Book(String title, String author, String isbn, String publication, int year, int copiesAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publication = publication;
        this.year = year;
        this.copiesAvailable = copiesAvailable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn +
                " | Title: " + title +
                " | Author: " + author +
                " | Publisher: " + publication +
                " | Year: " + year +
                " | Copies: " + copiesAvailable;
    }
}
