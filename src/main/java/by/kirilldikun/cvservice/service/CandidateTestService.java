package by.kirilldikun.cvservice.service;

import by.kirilldikun.cvservice.dto.CandidateTestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CandidateTestService {

    Page<CandidateTestDto> findAll(Pageable pageable);

    CandidateTestDto save(CandidateTestDto candidateTestDto);
}
