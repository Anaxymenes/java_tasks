package dev.anaxymenes.springboot.service.impl;

import dev.anaxymenes.springboot.mapper.AuthorMapper;
import dev.anaxymenes.springboot.mapper.impl.SimpleAuthorMapper;
import dev.anaxymenes.springboot.model.dto.Author;
import dev.anaxymenes.springboot.model.response.AuthorResponse;
import dev.anaxymenes.springboot.repository.AuthorRepository;
import dev.anaxymenes.springboot.service.AuthorService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthorDtos() {
        return authorRepository.findAll();
    }

    // TODO refaktor metody
    /*
        1. Zamienić na stream
        2. Usunąć duplikaty
     */
    @Override
    public List<AuthorResponse> getAllAuthorsWithNoBooks() {
        final List<Author> allAuthors = getAllAuthorDtos();
        final AuthorMapper mapper = new SimpleAuthorMapper();
        final List<AuthorResponse> responseList = new ArrayList<>();

        for(Author author: allAuthors){
            responseList.add(mapper.map(author));
        }
        return responseList;
    }
}
