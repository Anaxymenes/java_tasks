package dev.anaxymenes.springboot.service.impl;

import dev.anaxymenes.springboot.error.RecordNotFound;
import dev.anaxymenes.springboot.model.dto.Book;
import dev.anaxymenes.springboot.model.response.BookResponse;
import dev.anaxymenes.springboot.repository.BookRepository;
import dev.anaxymenes.springboot.service.BookService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    // TODO zmienić foreach na stream
    public List<BookResponse> getAllBooks() {
        final List<Book> bookList = bookRepository.findAll();
        final List<BookResponse> bookResponseList = new ArrayList<>();
        for(Book book:bookList){
            bookResponseList.add(mapBookDtoToBookResponse(book));
        }
        return bookResponseList;
    }

    @Override
    public BookResponse getFirstBook() {
        final Book bookDto = getFirstBookDto();
        return mapBookDtoToBookResponse(bookDto);
    }

    @Override
    public Book getFirstBookDto() {
        final Optional<Book> book = bookRepository.findAll().stream().findFirst();
        if(book.isPresent()){
            return book.get();
        }else{
            throw new RecordNotFound("Cannot find any records.");
        }
    }

    @Override
    public String getOffer() {
        // TODO Metoda ma pobierać wszystkie książki a następnie zwracać je w następujący sposób (Lista posortowana alfabetycznie po tytułach i nazwiskach autora)
        /*
         Lista dostępnych książek:
         ksiązka1.tytuł - autor.imie autor.nazwisko,
         książka2.tytuł - autor.imie autor.nazwisko,
         książka3.tytuł - autor.imie autor.nazwisko
         Reszta biblioteki w przygotowaniu
         */
        throw new UnsupportedOperationException();
    }


    private BookResponse mapBookDtoToBookResponse(Book book){
        final BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setAuthor(book.getAuthor().getFirstName());
        return bookResponse;
    }

}
