package com.tajohnson.bookclub.controllers.book;

import com.tajohnson.bookclub.models.book.Book;
import com.tajohnson.bookclub.models.user.User;
import com.tajohnson.bookclub.services.book.BookService;
import com.tajohnson.bookclub.services.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class BookController {
  @Autowired
  private BookService bookService;

  @Autowired
  private UserService userService;

  @GetMapping("/dashboard")
  public String dashboard(HttpSession session, Model model) {
    UUID userId = (UUID) session.getAttribute("userId");

    if (userService.isValidId(userId)) {
      model.addAttribute("userId", userId);
      String userName = userService.getUserByUuid(userId).getUserName();
      model.addAttribute("userName", userName);

      model.addAttribute("allBooks", bookService.allBooks());

      return "/book/dashboard.jsp";
    }

    return "redirect:/";
  }

  @GetMapping("/books/new/form")
  public String displayBookForm(
    HttpSession session,
    @ModelAttribute("book") Book book,
    Model model
  ) {
    UUID userId = (UUID) session.getAttribute("userId");

    if (userService.isValidId(userId)) {
      model.addAttribute("userId", userId);

      return "/book/bookForm.jsp";
    }

    return "redirect:/";
  }

  @PostMapping("/books/new/create")
  public String createBook(
    @Valid @ModelAttribute("book") Book book,
    BindingResult result,
    HttpSession session,
    Model model
  ) {
    if (result.hasErrors()) {
      model.addAttribute("book", book);

      return "book/bookForm.jsp";
    }
    Book newBook = bookService.createBook(book);
    User user = userService.getUserByUuid(
      (UUID) session.getAttribute("userId")
    );
    user.getBooks().add(newBook);

    return String.format("redirect:/books/%d", newBook.getId());
  }

  @GetMapping("/books/{id}")
  public String displayBook(@PathVariable("id") Long id, Model model) {
    model.addAttribute(bookService.getBookById(id));

    return "book/viewBook.jsp";
  }
}