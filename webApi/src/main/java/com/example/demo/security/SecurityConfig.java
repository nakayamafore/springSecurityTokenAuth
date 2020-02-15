package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.GenericFilterBean;
import com.example.demo.repository.UserRepository;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  CustomAuthenticationProvider authenticationProvider;

  @Value("${security.secret-key:secret}")
  private String secretKey = "secret";

  @Override
  protected void configure(final HttpSecurity http) throws Exception {

    http
        // AUTHORIZE
        .authorizeRequests().mvcMatchers("/hello/**").permitAll()
        .mvcMatchers("/user/**").hasRole("USER")
        .mvcMatchers("/admin/**").hasRole("ADMIN").anyRequest()
        .authenticated().and()
        // EXCEPTION
        .exceptionHandling()
        .authenticationEntryPoint(authenticationEntryPoint())
        .accessDeniedHandler(accessDeniedHandler()).and()
        // LOGIN
        .addFilter(loginFilter())
        // LOGOUT
        .logout().logoutUrl("/logout")
        .logoutSuccessHandler(logoutSuccessHandler()).and()
        // CSRF
        .csrf().disable()
        // AUTHORIZE
        .addFilterBefore(tokenFilter(),
            CustomAuthenticationFilter.class)
        // SESSION
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  /**
   * 認証処理に使用するユーザ情報クラス、パスワードエンコーダを登録
   * 
   * @param auth
   * @throws Exception
   */
  @Autowired
  public void configureGlobal(final AuthenticationManagerBuilder auth)
      throws Exception {
    auth.authenticationProvider(authenticationProvider);
  }

  @Bean("UserDetailsServiceImpl")
  UserDetailsServiceImpl userDetailsServiceImpl() {
    return new UserDetailsServiceImpl(userRepository);
  }

  /**
   * パスワードエンコーダの指定
   * 
   * @return パスワードエンコーダ
   */
  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  /**
   * ログインフィルタ
   * 
   * @return ログインフィルタ
   * @throws Exception 認証マネージャの取得に失敗した場合
   */
  private UsernamePasswordAuthenticationFilter loginFilter()
      throws Exception {
    // ログイン処理のカスタマイズ
    final CustomAuthenticationFilter loginfilter =
        new CustomAuthenticationFilter();
    loginfilter.setRequiresAuthenticationRequestMatcher(
        new AntPathRequestMatcher("/login", "POST"));
    loginfilter.setAuthenticationManager(authenticationManagerBean());
    return loginfilter;
  }

  /**
   * アクセストークンの認証filter
   * 
   * @return トークン認証フィルタ
   */
  private GenericFilterBean tokenFilter() {
    return new TokenFilter(userRepository, secretKey);
  }

  /**
   * 認証情報なしにアクセスした場合の処理を登録
   * 
   * @return
   */
  private AuthenticationEntryPoint authenticationEntryPoint() {
    return new WebApiAuthenticationEntryPoint();
  }

  /**
   * 認可処理に失敗した場合の処理を登録
   * 
   * @return
   */
  private AccessDeniedHandler accessDeniedHandler() {
    return new WebApiAccessDeniedHandler();
  }

  /**
   * ログアウト成功時の処理を登録
   * 
   * @return
   */
  private LogoutSuccessHandler logoutSuccessHandler() {
    return new HttpStatusReturningLogoutSuccessHandler();
  }

}
