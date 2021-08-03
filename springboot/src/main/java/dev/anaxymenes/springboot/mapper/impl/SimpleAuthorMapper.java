package dev.anaxymenes.springboot.mapper.impl;

import dev.anaxymenes.springboot.mapper.AuthorMapper;
import dev.anaxymenes.springboot.model.dto.Author;
import dev.anaxymenes.springboot.model.response.AuthorResponse;
import dev.anaxymenes.springboot.model.response.AuthorResponse.SimpleAuthorResponse;

public class SimpleAuthorMapper implements AuthorMapper {
    @Override
    public AuthorResponse map(Author dto) {
        return new SimpleAuthorResponse(dto.getFirstName(),dto.getLastName());
    }
}
