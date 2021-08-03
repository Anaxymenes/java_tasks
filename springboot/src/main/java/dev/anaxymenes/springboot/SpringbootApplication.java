package dev.anaxymenes.springboot;

import dev.anaxymenes.springboot.model.dto.Author;
import dev.anaxymenes.springboot.model.dto.Book;
import dev.anaxymenes.springboot.repository.AuthorRepository;
import dev.anaxymenes.springboot.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootApplication.class, args);

        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
        BookRepository bookRepository = context.getBean(BookRepository.class);

        Author author1 = new Author("John","Doe");
        Author author2 = new Author("Adam","Lorem");
        Author author3 = new Author("Jan","Kowalski");
        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);

        Book book1 = new Book("Lorem ipsum",author1);
        Book book2 = new Book("Cras ac urna ac turpis porttitor faucibus",author2);
        Book book3 = new Book("Maecenas efficitur",author2);
        Book book4 = new Book("Mauris lacinia",author3);
        Book book5 = new Book("Phasellus condimentum",author1);
        Book book6 = new Book("Mauris sollicitudin",author1);
        Book book7 = new Book("Vivamus tristique",author3);
        Book book8 = new Book("Quisque et nunc",author2);
        Book book9 = new Book("Aliquam blandit",author1);
        Book book10 = new Book("Curabitur blandit ",author2);
        Book book11 = new Book("Duis non metus",author1);
        Book book12 = new Book("Integer accumsan",author3);
        Book book13 = new Book("Vestibulum ac diam",author1);
        Book book14 = new Book("Donec iaculis felis",author2);
        Book book15 = new Book("Nullam in ante ornare",author3);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);
        bookRepository.save(book6);
        bookRepository.save(book7);
        bookRepository.save(book8);
        bookRepository.save(book9);
        bookRepository.save(book10);
        bookRepository.save(book11);
        bookRepository.save(book12);
        bookRepository.save(book13);
        bookRepository.save(book14);
        bookRepository.save(book15);
    }

}
