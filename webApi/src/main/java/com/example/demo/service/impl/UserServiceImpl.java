package com.example.demo.service.impl;

import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public Optional<User> findByName(final String name) {
    Objects.requireNonNull(name, "name must be not null");
    return userRepository.findFirstByName(name);
  }

}
