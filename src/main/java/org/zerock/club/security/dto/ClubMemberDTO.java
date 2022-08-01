package org.zerock.club.security.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClubMemberDTO {

  private String email;
  private String userName;
  private String password;
  private String name;
  private boolean fromSocial;
  private LocalDateTime regDate;
  private LocalDateTime modDate;

  @Builder.Default
  private Set<String> roleSet = new HashSet<>();
}
