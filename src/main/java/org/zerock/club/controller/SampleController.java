package org.zerock.club.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.club.security.dto.ClubAuthMemberDTO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {

  @GetMapping("/all")
  public void all() {
    log.info("exAll........");
  }

  @GetMapping("/member")
  public void member(@AuthenticationPrincipal ClubAuthMemberDTO dto) {
    log.info("exMember........");
    log.info(dto);
  }

  @GetMapping("/admin")
  public void admin() {
    log.info("exAdmin........");
  }

}
