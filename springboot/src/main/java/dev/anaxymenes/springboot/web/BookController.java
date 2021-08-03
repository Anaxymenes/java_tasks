package dev.anaxymenes.springboot.web;

import dev.anaxymenes.springboot.model.response.BookResponse;
import dev.anaxymenes.springboot.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books/")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookResponse>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping(path = "dto/first",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponse> getFirstBookDto(){
        return new ResponseEntity<>(bookService.getFirstBook(), HttpStatus.OK);
    }

    @GetMapping(path = "offer", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String getOffer(){
        return bookService.getOffer();
    }

}
