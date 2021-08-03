package dev.anaxymenes.springboot.mapper;

import dev.anaxymenes.springboot.model.dto.Author;
import dev.anaxymenes.springboot.model.response.AuthorResponse;

public interface AuthorMapper {
    AuthorResponse map(Author dto);
}
