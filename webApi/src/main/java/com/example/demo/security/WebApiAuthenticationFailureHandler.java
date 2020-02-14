package com.example.demo.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 認証が失敗した時の処理(403 Forbidden)
 */
@RequiredArgsConstructor
@Slf4j
public class WebApiAuthenticationFailureHandler
    implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final AuthenticationException exception)
      throws IOException, ServletException {
    if (response.isCommitted()) {
      log.info("Response has already been committed.");
      return;
    }
    // レスポンスに403を設定して終了
    response.sendError(HttpStatus.FORBIDDEN.value(),
        HttpStatus.FORBIDDEN.getReasonPhrase());
  }

}
