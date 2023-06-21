package com.tajohnson.bookclub.service.user;

import com.tajohnson.authentication.models.LoginUser;
import com.tajohnson.authentication.models.User;
import com.tajohnson.authentication.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public User login(LoginUser newLogin, BindingResult result) {
    if (result.hasErrors()) {
      return null;
    }

    Optional<User> user = userRepository.findByEmail(newLogin.getEmail());

    if (user.isEmpty()) {
      result.rejectValue(
        "email",
        "EMAIL-NOT-PRESENT",
        "User not found. Check the e-mail and try again or register a new user"
      );
      return null;
    }

    User foundUser = user.get();

    if (!BCrypt.checkpw(newLogin.getPassword(), foundUser.getPassword())) {
      result.rejectValue(
        "password",
        "INVALID-LOGIN-PW",
        "Incorrect password. Please try again"
      );
      return null;
    }

    return foundUser;
  }

  public User register(User newUser, BindingResult result) {
    if (result.hasErrors()) {
      return null;
    }

    if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
      result.rejectValue(
        "confirmPassword",
        "PW-MISMATCH",
        "Passwords must match"
      );
      return null;
    }

    if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
      result.rejectValue(
        "email",
        "EMAIL-PRESENT",
        "There is already an account with this email"
      );
      return null;
    }

    String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    newUser.setPassword(hashedPassword);

    return userRepository.save(newUser);
  }

  public User getUserByUuid(UUID uuid) {
    Optional<User> optional = userRepository.findById(uuid);

    return optional.orElse(null);
  }

  public boolean isValidId(UUID uuid) {
    return getUserByUuid(uuid) != null;
  }
}