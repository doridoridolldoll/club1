package org.zerock.club.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//로그인 후 로그인 정보를 가지고 세션을 만들기 위한 객체
//Object for creating a session with login information after login
public class ClubAuthMemberDTO extends User {
  private String email;
  private String name;
  private boolean fromSocial;

  public ClubAuthMemberDTO(String username, String password,
      boolean fromSocial, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    email = username; // ★ security와 사용자가 생성한 DB의 계정을 매칭
    this.fromSocial = fromSocial;
  }
}
