package com.example.demo.security;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 認証が成功した時の処理
 */
@RequiredArgsConstructor
@Slf4j
public class WebApiAuthenticationSuccessHandler
    implements AuthenticationSuccessHandler {

  private final Algorithm algorithm;
  // アクセストークンの有効期限(30分)
  private static final Long EXPIRATION_TIME =
      TimeUnit.MINUTES.toMillis(30L);

  public WebApiAuthenticationSuccessHandler(final String secretKey) {
    Objects.requireNonNull(secretKey, "secret key must be not null");
    this.algorithm = Algorithm.HMAC512(secretKey);
  }

  @Override
  public void onAuthenticationSuccess(
      final HttpServletRequest request,
      final HttpServletResponse response, final Authentication auth) {
    if (response.isCommitted()) {
      log.info("Response has already been committed.");
      return;
    }
    // 認証が成功したので、アクセストークンを返却
    setToken(response, generateToken(auth));
    // ステータス200
    response.setStatus(HttpStatus.OK.value());
    // リクエストに使われた認証情報を削除
    clearAuthenticationAttributes(request);
  }

  /**
   * アクセストークンを生成する
   * 
   * @param auth
   * @return
   */
  private String generateToken(final Authentication auth) {
    final LoginUser loginUser = (LoginUser) auth.getPrincipal();
    // 作成日
    final Date issuedAt = new Date();
    final Date notBefore = new Date(issuedAt.getTime());
    // アクセストークンの有効期限
    final Date expiresAt =
        new Date(issuedAt.getTime() + EXPIRATION_TIME);
    // アクセストークンをJWTで生成
    final String token = JWT.create().withIssuedAt(issuedAt)
        .withNotBefore(notBefore).withExpiresAt(expiresAt)
        .withSubject(loginUser.getUser().getId().toString())
        .sign(this.algorithm);
    log.debug("generate token : {}", token);
    return token;
  }

  /**
   * アクセストークンをレスポンスヘッダーに設定
   * 
   * @param response レスポンス
   * @param token アクセストークン
   */
  private void setToken(final HttpServletResponse response,
      final String token) {
    // トークンの頭に"Bearer "を追加して設定
    response.setHeader("Authorization",
        String.format("Bearer %s", token));
  }

  /**
   * リクエストの認証情報を削除する
   */
  private void clearAuthenticationAttributes(
      final HttpServletRequest request) {
    final HttpSession session = request.getSession(false);
    if (session == null) {
      return;
    }
    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
  }

}
