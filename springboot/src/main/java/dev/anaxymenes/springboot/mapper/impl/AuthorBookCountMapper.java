package dev.anaxymenes.springboot.mapper.impl;

import dev.anaxymenes.springboot.mapper.AuthorMapper;
import dev.anaxymenes.springboot.model.dto.Author;
import dev.anaxymenes.springboot.model.response.AuthorResponse;
import dev.anaxymenes.springboot.model.response.AuthorResponse.AuthorBookCountResponse;

public class AuthorBookCountMapper implements AuthorMapper {
    @Override
    public AuthorResponse map(Author dto) {
        return new AuthorBookCountResponse(dto.getFirstName(),dto.getLastName(),dto.getBooks().size());
    }
}
