package org.zerock.club.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.club.entity.ClubMember;
import org.zerock.club.entity.ClubMemberRole;

@SpringBootTest
public class ClubMemberRepositoryTests {
  @Autowired
  private ClubMemberRepository repository;
  @Autowired
  private PasswordEncoder encoder;

  @Test
  public void insertDummies(){
    IntStream.rangeClosed(1, 100).forEach(i ->{
      ClubMember clubMember = ClubMember.builder()
      .email("user"+i+"@zerock.org")
      .name("사용자"+i)
      .fromSocial(false)
      .password(encoder.encode("1"))
      .build();
      clubMember.addMemberRole(ClubMemberRole.USER);
      if(i>80) clubMember.addMemberRole(ClubMemberRole.MANAGER);
      if(i>90) clubMember.addMemberRole(ClubMemberRole.ADMIN);
      repository.save(clubMember);
    });
  }

  @Test
  public void testRead(){
    Optional<ClubMember> result = repository.findByEmail("user95@zerock.org", false);
    if(result.isPresent()) {
      ClubMember clubMember = result.get();
      System.out.println("findByEmail: " + clubMember);
    }
  }
}
