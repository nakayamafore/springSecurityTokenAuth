package com.example.demo.security;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.CsrfException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * アクセスするリソースの認可に失敗した時の処理(403 Forbidden)
 */
@NoArgsConstructor
@Slf4j
public class WebApiAccessDeniedHandler
    implements AccessDeniedHandler {
  @Override
  public void handle(final HttpServletRequest request,
      final HttpServletResponse response,
      final AccessDeniedException exception) throws IOException {
    log.info("--WebApiAccessDeniedHandler");
    if (response.isCommitted()) {
      log.info("Response has already been committed.");
      return;
    }
    dump(exception);
    response.sendError(HttpStatus.FORBIDDEN.value(),
        HttpStatus.FORBIDDEN.getReasonPhrase());
  }

  /**
   * エラー情報をlog出力
   * 
   * @param e
   */
  private void dump(final AccessDeniedException e) {
    if (e instanceof AuthorizationServiceException) {
      log.debug("AuthorizationServiceException : {}", e.getMessage());
    } else if (e instanceof CsrfException) {
      log.debug(
          "org.springframework.security.web.csrf.CsrfException : {}",
          e.getMessage());
    } else if (e instanceof org.springframework.security.web.server.csrf.CsrfException) {
      log.debug(
          "org.springframework.security.web.server.csrf.CsrfException : {}",
          e.getMessage());
    } else {
      log.debug("AccessDeniedException : {}", e.getMessage());
    }
  }

}
