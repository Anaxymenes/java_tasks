package dev.anaxymenes.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.anaxymenes.springboot.model.dto.Author;
import dev.anaxymenes.springboot.model.dto.Book;
import dev.anaxymenes.springboot.model.response.BookResponse;
import dev.anaxymenes.springboot.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @MockBean
    private BookRepository bookRepository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        final Author author1 = new Author(1L,"John","Doe");
        final Author author2 = new Author(2L,"Adam","Lorem");

        final Book book1 = new Book(1L,"Lorem ipsum",author1);
        final Book book2 = new Book(2L,"Cras ac urna ac turpis porttitor faucibus",author2);
        final Book book3 = new Book(3L,"Maecenas efficitur",author2);
        final Book book4 = new Book(4L,"Phasellus condimentum",author1);
        final Book book5 = new Book(5L,"Mauris sollicitudin",author1);


        final List<Book> bookList = Arrays.asList(book1,book2,book3,book4,book5);
        when(bookRepository.findAll()).thenReturn(bookList);
        when(bookRepository.findOne(any())).thenReturn(Optional.of(book1));
    }

    @Test
    public void shouldReturnStatusOk() throws Exception{
        this.mockMvc.perform(get("/books/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void shouldReturnAllBooks() throws Exception{
        //given
        final List<BookResponse> bookResponseList = Arrays.asList(
                new BookResponse(1L,"Lorem ipsum","John Doe"),
                new BookResponse(2L,"Cras ac urna ac turpis porttitor faucibus","Adam Lorem"),
                new BookResponse(3L,"Maecenas efficitur","Adam Lorem"),
                new BookResponse(4L,"Phasellus condimentum","John Doe"),
                new BookResponse(5L,"Mauris sollicitudin","John Doe")
        );
        final String expectedBookResponse = new ObjectMapper().writeValueAsString(bookResponseList);


        //when
        final MvcResult result = this.mockMvc.perform(get("/books/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final String jsonResult = result.getResponse().getContentAsString();

        //then
        assertEquals(expectedBookResponse,jsonResult,"Response mismatch");
    }

    @Test
    public void shouldReturnFirstBookDto() throws Exception{
        //given
        final BookResponse expectedBookResponse = new BookResponse(1L,"Lorem ipsum","John Doe");
        final String jsonExpected = new ObjectMapper().writeValueAsString(expectedBookResponse);

        //when
        final MvcResult result = this.mockMvc.perform(get("/books/dto/first"))
                .andExpect(status().isOk())
                .andReturn();
        final String jsonActual = result.getResponse().getContentAsString();

        //then
        assertEquals(jsonExpected,jsonActual,"Response mismatch");

    }

    @Test
    public void shouldReturnOfferString() throws Exception{
        //given
        final String expectedJson = "" +
                "Lorem ipsum - John Doe,\n" +
                "Cras ac urna ac turpis porttitor faucibus - Adam Lorem,\n" +
                "Maecenas efficitur - Adam Lorem,\n" +
                "Phasellus condimentum - John Doe,\n" +
                "Mauris sollicitudin - John Doe.\n" +
                "Reszta biblioteki w przygotowaniu.";

        //when
        final MvcResult result = this.mockMvc.perform(get("/books/offer"))
                .andExpect(status().isOk())
                .andReturn();
        final String jsonActual = result.getResponse().getContentAsString();

        //then
        assertEquals(expectedJson,jsonActual);
    }


}