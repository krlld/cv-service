package by.kirilldikun.cvservice.service;

import by.kirilldikun.cvservice.dto.CandidateDto;
import by.kirilldikun.cvservice.dto.FileInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CandidateService {

    Page<CandidateDto> findAll(String query, Pageable pageable);

    CandidateDto findById(Long id);

    CandidateDto save(CandidateDto candidateDto);

    FileInfoDto findPhotoByCandidateId(Long candidateId);

    FileInfoDto findCvByCandidateId(Long candidateId);
}
