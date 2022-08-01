package org.zerock.club.security.service;

import org.springframework.stereotype.Service;
import org.zerock.club.repository.ClubMemberRepository;
import org.zerock.club.security.dto.ClubMemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClubMemberServiceImpl implements ClubMemberSerivce {
  
  private final ClubMemberRepository repository;

  @Override
  public void modifyClubMember(ClubMemberDTO dto) {
    log.info("modifyClubMember... dto: " + dto);
    repository.save(dtoToEntity(dto));
  }
}
