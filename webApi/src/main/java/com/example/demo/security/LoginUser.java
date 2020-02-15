package com.example.demo.security;

import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import com.example.demo.entity.User;
import lombok.Getter;

@Getter
public class LoginUser
    extends org.springframework.security.core.userdetails.User {
  private static final long serialVersionUID = 1L;

  // Userエンティティ
  private com.example.demo.entity.User user;

  // ロールの種類
  private static final List<GrantedAuthority> USER_ROLES =
      AuthorityUtils.createAuthorityList("ROLE_USER");
  private static final List<GrantedAuthority> ADMIN_ROLES =
      AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");

  /**
   * ログインユーザ情報を生成
   * 
   * @param user ユーザ情報
   */
  public LoginUser(final User user) {
    super(user.getName(), user.getPassword(),
        determineRoles(user.getAdminFlag()));
    this.user = user;
  }

  /**
   * ロール取得
   * 
   * @param isAdmin 管理者フラグ
   * @return ロール情報
   */
  private static List<GrantedAuthority> determineRoles(
      final boolean isAdmin) {
    return isAdmin ? ADMIN_ROLES : USER_ROLES;
  }
}
