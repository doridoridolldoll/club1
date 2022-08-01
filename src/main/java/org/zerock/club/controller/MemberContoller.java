package org.zerock.club.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
  public void modify() {
    log.info("/member/modify");
  }
}