package dev.anaxymenes.springboot.repository;

import dev.anaxymenes.springboot.model.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
