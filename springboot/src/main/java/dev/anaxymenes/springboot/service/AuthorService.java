package dev.anaxymenes.springboot.service;

import dev.anaxymenes.springboot.model.dto.Author;
import dev.anaxymenes.springboot.model.response.AuthorResponse;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthorDtos();

    List<AuthorResponse> getAllAuthorsWithNoBooks();
}
