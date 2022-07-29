package org.zerock.club.security.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.club.entity.ClubMember;
import org.zerock.club.repository.ClubMemberRepository;
import org.zerock.club.security.dto.ClubAuthMemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
//로그인 처리해주는 클래스
public class ClubUserDetailsService implements UserDetailsService {

  private final ClubMemberRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("ClubUserDetailsService loadUserByUsername: " + username);
    Optional<ClubMember> result = repository.findByEmail(username, false);
    if (!result.isPresent()) {
      throw new UsernameNotFoundException("Check email or Social");
    }
    ClubMember cm = result.get();
    log.info("clubMember: " + cm);

    //ClubAuthMemberDTO는 User를 상속받았기 때문.
    ClubAuthMemberDTO clubAuthMember = new ClubAuthMemberDTO(
        cm.getEmail(), cm.getPassword(), cm.isFromSocial(),
        cm.getRoleSet().stream().map(role -> 
          new SimpleGrantedAuthority("ROLE_" + role.name()))
          .collect(Collectors.toList()));
    clubAuthMember.setName(cm.getName());
    clubAuthMember.setFromSocial(cm.isFromSocial());
    return clubAuthMember;
  }
}
