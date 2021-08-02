package dev.anaxymenes;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// TODO Napisać następujące funkcje (z wykorzystaniem stream)
/*
    1. zwracającą listę książek, które zostały wydane
    2. zwracającą wartość typu String, który jako parametr przyjmuje listę książek, które nie zostały wydane.
        Wynikowy string składa się z tytułów książek oddzielonych przecinkiem np "Lorem ipsum, Integer varius"
 */
public class StreamApp {
    public static void main(String[] args) {

    }

    private static List<Book> getAllBooks(){
        return Arrays.asList(
                new Book(1, "Lorem ipsum dolor",false),
                new Book(2, "Maecenas vestibulum",true),
                new Book(3, "Integer varius",true),
                new Book(4, "Sed ultrices nunc",false),
                new Book(5, "Donec sit amet lacus a erat",false),
                null,
                new Book(6, "Mauris fermentum nisi sodales",true),
                new Book(7, "Nulla cursus orci ac nibh",true),
                new Book(8, "Sed quis nibh volutpat odio",true)
        );
    }

    static class Book{
        private long id;
        private String title;
        private boolean released;

        public Book(long id, String title, boolean released) {
            this.id = id;
            this.title = title;
            this.released = released;
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

        public boolean isReleased() {
            return released;
        }

        public void setReleased(boolean released) {
            this.released = released;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Book book = (Book) o;
            return id == book.id && released == book.released && Objects.equals(title, book.title);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, title, released);
        }

        @Override
        public String toString() {
            return "Book{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", released=" + released +
                    '}';
        }
    }
}
