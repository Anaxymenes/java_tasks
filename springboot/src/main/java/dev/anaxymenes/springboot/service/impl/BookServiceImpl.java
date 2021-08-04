package dev.anaxymenes.springboot.service.impl;

import dev.anaxymenes.springboot.model.dto.Book;
import dev.anaxymenes.springboot.model.response.BookResponse;
import dev.anaxymenes.springboot.repository.BookRepository;
import dev.anaxymenes.springboot.service.BookService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        // TODO implementacja findFirst za pomocą stream i optional. Jeśli nie znajdzie nic, niech rzuci wyjątek RecordNotFound

        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public String getOffer() {
        // TODO Metoda ma pobierać wszystkie książki a następnie zwracać je w następujący sposób (Lista posortowana alfabetycznie po tytułach i nazwiskach autora)
        /*
         ksiązka1.tytuł - autor.imie autor.nazwisko,\n
         książka2.tytuł - autor.imie autor.nazwisko,\n
         książka3.tytuł - autor.imie autor.nazwisko.\n
         Reszta biblioteki w przygotowaniu.
         */
        throw new UnsupportedOperationException("Method not implemented");
    }


    private BookResponse mapBookDtoToBookResponse(Book book){
        final BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setAuthor(book.getAuthor().getFirstName());
        return bookResponse;
    }

}
