package com.example.demo.repository;

import java.util.Optional;
import com.example.demo.entity.User;

public interface UserRepository {
  Optional<User> findByEmail(String email);

  Optional<User> findFirstByName(String name);

  Optional<User> findById(Long id);
}
