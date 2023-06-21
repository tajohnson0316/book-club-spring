package com.tajohnson.bookclub.repositories.book;

import com.tajohnson.bookclub.models.book.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
  List<Book> findAll();
}