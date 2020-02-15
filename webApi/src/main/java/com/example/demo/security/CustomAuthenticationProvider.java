package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.demo.exception.WebApiAuthenticationException;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CustomAuthenticationProvider
    implements AuthenticationProvider {

  @Autowired
  private UserDetailsServiceImpl userDetailsService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(
      final Authentication authentication)
      throws AuthenticationException {
    log.info("--CustomAuthenticationProvider.authenticate");
    // CustomAuthenticationFilterクラスから渡されたものを取得
    final String email = (String) authentication.getPrincipal();
    final String pass = (String) authentication.getCredentials();

    if (email == null) {
      throw new WebApiAuthenticationException("認証情報がありません");
    }

    // ユーザ情報の存在チェック
    final LoginUser loginUser =
        userDetailsService.loadUserByUsername(email);
    if (loginUser == null) {
      throw new WebApiAuthenticationException("ユーザが存在しません");
    }
    // パスワードのチェック
    if (!passwordEncoder.matches(pass, loginUser.getPassword())) {
      throw new WebApiAuthenticationException("パスワードが間違っています");
    }
    // 認証結果情報を返却
    return new UsernamePasswordAuthenticationToken(loginUser,
        loginUser.getPassword(), loginUser.getAuthorities());
  }

  @Override
  public boolean supports(final Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class
        .isAssignableFrom(authentication);
  }

}
