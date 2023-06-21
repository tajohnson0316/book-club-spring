package com.tajohnson.authentication.controllers;

import com.tajohnson.authentication.models.LoginUser;
import com.tajohnson.authentication.models.User;
import com.tajohnson.authentication.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class HomeController {
  @Autowired
  private UserService userService;

  @GetMapping("/")
  public String index(HttpSession session, Model model) {
    if (session.getAttribute("userId") != null) {
      return "redirect:/home";
    }

    model.addAttribute("newUser", new User());
    model.addAttribute("newLogin", new LoginUser());

    return "loginReg.jsp";
  }

  @PostMapping("/users/register")
  public String registerNewUser(
    @Valid @ModelAttribute("newUser") User newUser,
    BindingResult result,
    Model model,
    HttpSession session
  ) {
    User user = userService.register(newUser, result);

    if (result.hasErrors()) {
      model.addAttribute("newLogin", new LoginUser());

      return "loginReg.jsp";
    }

    session.setAttribute("userId", user.getId());

    return "redirect:/home";
  }

  @PostMapping("/users/login")
  public String loginUser(
    @Valid @ModelAttribute("newLogin") LoginUser newLogin,
    BindingResult result,
    Model model,
    HttpSession session
  ) {
    User user = userService.login(newLogin, result);

    if (result.hasErrors()) {
      model.addAttribute("newUser", new User());

      return "loginReg.jsp";
    }

    session.setAttribute("userId", user.getId());

    return "redirect:/home";
  }

  @GetMapping("/home")
  public String home(HttpSession session, Model model) {
    UUID userId = (UUID) session.getAttribute("userId");

    if (userService.isValidId(userId)) {
      String userName = userService.getUserByUuid(userId).getUserName();
      model.addAttribute("userName", userName);

      return "home.jsp";
    }

    return "redirect:/";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }
}