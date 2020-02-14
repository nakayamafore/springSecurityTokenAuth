package com.example.demo.security;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

/**
 * Userエンティティ
 */
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailsServiceImpl(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(final String email) {
    // emailでデータベースからユーザーエンティティを検索する
    final Optional<User> userOpt = userRepository.findByEmail(email);
    return userRepository.findByEmail(email).map(LoginUser::new)
        .orElseThrow(
            () -> new UsernameNotFoundException("user not found"));
  }

}
