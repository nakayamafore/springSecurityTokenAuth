package com.example.demo.security;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * アクセストークン認証filter
 *
 */
@RequiredArgsConstructor
@Slf4j
public class TokenFilter extends GenericFilterBean {
  private final UserRepository userRepository;
  private final Algorithm algorithm;

  public TokenFilter(final UserRepository userRepository,
      final String secretKey) {
    Objects.requireNonNull(secretKey, "secret key must be not null");
    this.userRepository = userRepository;
    this.algorithm = Algorithm.HMAC512(secretKey);
  }

  /**
   * アクセストークン認証処理
   */
  @Override
  public void doFilter(final ServletRequest request,
      final ServletResponse response, final FilterChain filterChain)
      throws IOException, ServletException {
    // リクエストからトークンを取り出す
    final String token = resolveToken(request);
    if (token == null) {
      // トークンがなければ他のフィルターへ
      filterChain.doFilter(request, response);
      return;
    }

    try {
      // アクセストークンの検証処理
      authentication(verifyToken(token));
    } catch (final JWTVerificationException e) {
      log.error("verify token error", e);
      SecurityContextHolder.clearContext();
      // 検証失敗時は401:UnAuthorizedで返却する
      ((HttpServletResponse) response).sendError(
          HttpStatus.UNAUTHORIZED.value(),
          HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }
    filterChain.doFilter(request, response);

  }

  /**
   * アクセストークンをリクエストHeaderから取り出す
   * 
   * @param request Httpリクエスト
   * @return アクセストークン
   */
  private String resolveToken(final ServletRequest request) {
    final String token =
        ((HttpServletRequest) request).getHeader("Authorization");
    // アクセストークンの先頭にBearerがついてないとトークンと認めない
    if (token == null || !token.startsWith("Bearer ")) {
      return null;
    }
    // "Bearer "を除いたアクセストークンを取り出す
    return token.substring(7);
  }

  /**
   * トークンの検証を実施する
   * 
   * @param token アクセストークン
   * @return デコード済みのトークン
   */
  private DecodedJWT verifyToken(final String token) {
    final JWTVerifier verifier = JWT.require(algorithm).build();
    return verifier.verify(token);
  }

  /**
   * トークンを元に認証処理を実施する
   * 
   * @param jwt デコード済みトークン
   */
  private void authentication(final DecodedJWT jwt) {
    // トークンのsubjectを取り出す
    final Long userId = Long.valueOf(jwt.getSubject());
    // トークンのuserIdを検索し、ある場合は認証情報を作成する
    userRepository.findById(userId).ifPresent(user -> {
      final LoginUser loginUser = new LoginUser(user);
      SecurityContextHolder.getContext().setAuthentication(
          new UsernamePasswordAuthenticationToken(loginUser, null,
              loginUser.getAuthorities()));
    });
  }

}
