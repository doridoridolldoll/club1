package org.zerock.club.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.club.security.dto.ClubAuthMemberDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/member")
public class MemberContoller {

  @GetMapping("/login")
  public void login() {
    log.info("/member/login");
  }

  @GetMapping("/modify")
  public void modify(@AuthenticationPrincipal ClubAuthMemberDTO dto, Model model) {
    log.info("/member/modify");
    model.addAttribute("auth", dto);
    List<String> roleNames = new ArrayList<>();
    dto.getAuthorities().forEach(new Consumer<GrantedAuthority>() {
      @Override
      public void accept(GrantedAuthority authority) {
        roleNames.add(authority.getAuthority());
      }
    });
    model.addAttribute("roleNames", roleNames);
  }
}