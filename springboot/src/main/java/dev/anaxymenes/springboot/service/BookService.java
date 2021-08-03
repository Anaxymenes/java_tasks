package dev.anaxymenes.springboot.service;

import dev.anaxymenes.springboot.model.dto.Book;
import dev.anaxymenes.springboot.model.response.BookResponse;

import java.util.List;

public interface BookService {
    List<BookResponse> getAllBooks();
    BookResponse getFirstBook();
    Book getFirstBookDto();
    String getOffer();
}
