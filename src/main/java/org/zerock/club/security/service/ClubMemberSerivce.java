package org.zerock.club.security.service;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.zerock.club.entity.ClubMember;
import org.zerock.club.entity.ClubMemberRole;
import org.zerock.club.security.dto.ClubAuthMemberDTO;
import org.zerock.club.security.dto.ClubMemberDTO;

public interface ClubMemberSerivce {

  void modifyClubMember(ClubMemberDTO dto);

  default ClubMember dtoToEntity(ClubMemberDTO dto) {
    ClubMember clubMember = ClubMember.builder()
        .email(dto.getEmail())
        .name(dto.getName())
        .fromSocial(dto.isFromSocial())
        .roleSet(dto.getRoleSet().stream().map(new Function<String, ClubMemberRole>() {
          @Override
          public ClubMemberRole apply(String t) {
            if (t.equals("ROLE_USER"))
              return ClubMemberRole.USER;
            else if (t.equals("ROLE_MANAGER"))
              return ClubMemberRole.MANAGER;
            else if (t.equals("ROLE_ADMIN"))
              return ClubMemberRole.ADMIN;
            else
              return ClubMemberRole.USER;
          }
        }).collect(Collectors.toSet()))
        .build();
    return clubMember;
  }

  default ClubMemberDTO entityTODTO(ClubMember clubMember) {
    ClubMemberDTO dto = ClubMemberDTO.builder()
        .email(clubMember.getEmail())
        .name(clubMember.getName())
        .userName(clubMember.getEmail())
        .fromSocial(clubMember.isFromSocial())
        .roleSet(clubMember.getRoleSet().stream().map(new Function<ClubMemberRole, String>() {
          @Override
          public String apply(ClubMemberRole t) {
            return new String("ROLE_" + t.name());
          }
        }).collect(Collectors.toSet()))
        .build();
    return dto;
  }
}
