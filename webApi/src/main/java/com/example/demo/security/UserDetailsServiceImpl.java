package com.example.demo.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * Userエンティティ
 */
@Slf4j
public class UserDetailsServiceImpl {

  private final UserRepository userRepository;

  public UserDetailsServiceImpl(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public LoginUser loadUserByUsername(final String email) {
    log.info("UserDetailsServiceImpl.loadUserByUsername");
    // emailでデータベースからユーザーエンティティを検索する
    return userRepository.findByEmail(email).map(LoginUser::new)
        .orElseThrow(
            () -> new UsernameNotFoundException("user not found"));
  }
}
