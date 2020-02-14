package com.example.demo.security;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 認証が必要なリソースに未認証でアクセスしたときの処理(401 Unauthorized)
 */
@NoArgsConstructor
@Slf4j
public class WebApiAuthenticationEntryPoint
    implements AuthenticationEntryPoint {
  @Override
  public void commence(final HttpServletRequest request,
      final HttpServletResponse response,
      final AuthenticationException exception) throws IOException {
    if (response.isCommitted()) {
      log.info("Response has already been committed.");
      return;
    }
    dump(exception);
    response.sendError(HttpStatus.UNAUTHORIZED.value(),
        HttpStatus.UNAUTHORIZED.getReasonPhrase());
  }

  /**
   * 認証失敗時のエラー情報をlog出力
   * 
   * @param e エラーオブジェクト
   */
  private void dump(final AuthenticationException e) {
    if (e instanceof BadCredentialsException) {
      log.debug("BadCredentialsException : {}", e.getMessage());
    } else if (e instanceof LockedException) {
      log.debug("LockedException : {}", e.getMessage());
    } else if (e instanceof DisabledException) {
      log.debug("DisabledException : {}", e.getMessage());
    } else if (e instanceof AccountExpiredException) {
      log.debug("AccountExpiredException : {}", e.getMessage());
    } else if (e instanceof CredentialsExpiredException) {
      log.debug("CredentialsExpiredException : {}", e.getMessage());
    } else if (e instanceof SessionAuthenticationException) {
      log.debug("SessionAuthenticationException : {}",
          e.getMessage());
    } else {
      log.debug("AuthenticationException : {}", e.getMessage());
    }
  }
}
