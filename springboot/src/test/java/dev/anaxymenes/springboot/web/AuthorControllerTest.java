package dev.anaxymenes.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.anaxymenes.springboot.model.dto.Author;
import dev.anaxymenes.springboot.model.dto.Book;
import dev.anaxymenes.springboot.model.response.AuthorResponse;
import dev.anaxymenes.springboot.model.response.AuthorResponse.AuthorBookCountResponse;
import dev.anaxymenes.springboot.repository.AuthorRepository;
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
class AuthorControllerTest {
    @MockBean
    private AuthorRepository authorRepository;
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

        author1.setBooks(Arrays.asList(book1,book4,book5));
        author2.setBooks(Arrays.asList(book2,book3));

        final List<Author> authors = Arrays.asList(author1,author2);

        when(authorRepository.findAll()).thenReturn(authors);
        when(authorRepository.findOne(any())).thenReturn(Optional.of(author1));
    }

    @Test
    public void shouldReturnAllAuthorsWithNumberOfBooks() throws Exception{
        //given
        final List<AuthorResponse> authorResponseList = Arrays.asList(
                new AuthorBookCountResponse("John","Doe",3),
                new AuthorBookCountResponse("Adam","Lorem",2)
        );
        final String expectedBookResponse = new ObjectMapper().writeValueAsString(authorResponseList);

        //when
        final MvcResult result = this.mockMvc.perform(get("/authors/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final String jsonResult = result.getResponse().getContentAsString();

        //then
        assertEquals(expectedBookResponse,jsonResult,"Response mismatch");
    }
}