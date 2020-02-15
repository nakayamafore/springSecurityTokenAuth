package com.example.demo.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.demo.model.request.WebApiAuthRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationFilter
    extends UsernamePasswordAuthenticationFilter {

  @Value("${security.secret-key:secret}")
  private String secretKey = "secret";

  /**
   * 認証処理の前処理
   */
  @Override
  public Authentication attemptAuthentication(
      final HttpServletRequest request,
      final HttpServletResponse response) {
    log.info("--CustomAuthenticationFilter.attemptAuthentication");
    // InputStreamのJsonテキストを取り出して, 認証情報に積める
    WebApiAuthRequest webApiAuthRequest = null;
    try {
      webApiAuthRequest = new ObjectMapper().readValue(
          request.getInputStream(), WebApiAuthRequest.class);
    } catch (final IOException e) {
      e.printStackTrace();
    }

    final UsernamePasswordAuthenticationToken authRequest =
        new UsernamePasswordAuthenticationToken(
            webApiAuthRequest.getEmail(),
            webApiAuthRequest.getPass());

    setDetails(request, authRequest);

    return this.getAuthenticationManager().authenticate(authRequest);
  }

  @Override
  protected void successfulAuthentication(
      final HttpServletRequest req, final HttpServletResponse res,
      final FilterChain chain, final Authentication auth)
      throws IOException, ServletException {
    // 認証結果をSpringSecurtyに報告
    SecurityContextHolder.getContext().setAuthentication(auth);

    new WebApiAuthenticationSuccessHandler(secretKey)
        .onAuthenticationSuccess(req, res, auth);
  }

  @Override
  protected void unsuccessfulAuthentication(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final AuthenticationException failed)
      throws IOException, ServletException {
    SecurityContextHolder.clearContext();

    new WebApiAuthenticationFailureHandler()
        .onAuthenticationFailure(request, response, failed);
  }
}
