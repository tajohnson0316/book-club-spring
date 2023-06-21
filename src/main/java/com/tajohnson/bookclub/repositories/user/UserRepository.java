package com.tajohnson.bookclub.repositories.user;

import com.tajohnson.bookclub.models.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
  Optional<User> findByEmail(String email);
}