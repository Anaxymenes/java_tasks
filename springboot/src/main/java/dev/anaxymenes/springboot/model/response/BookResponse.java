package dev.anaxymenes.springboot.model.response;

import java.util.Objects;

public class BookResponse {
    private long id;
    private String title;
    private String author;


    public BookResponse(long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public BookResponse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookResponse)) return false;
        BookResponse that = (BookResponse) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author);
    }

    @Override
    public String toString() {
        return "BookResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
