package com.tajohnson.bookclub.services.book;

import com.tajohnson.bookclub.models.book.Book;
import com.tajohnson.bookclub.repositories.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
  @Autowired
  private BookRepository bookRepository;

  public List<Book> allBooks() {
    return bookRepository.findAll();
  }

  public Book getBookById(Long id) {
    Optional<Book> optional = bookRepository.findById(id);

    return optional.orElse(null);
  }

  public Book createBook(Book book) {
    return bookRepository.save(book);
  }

  public Book updateBook(Book book) {
    return bookRepository.save(book);
  }

  public void deleteBook(Long id) {
    bookRepository.deleteById(id);
  }
}