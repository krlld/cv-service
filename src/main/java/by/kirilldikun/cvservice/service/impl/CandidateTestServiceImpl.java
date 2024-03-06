package by.kirilldikun.cvservice.service.impl;

import by.kirilldikun.cvservice.dto.CandidateTestDto;
import by.kirilldikun.cvservice.dto.ResultDto;
import by.kirilldikun.cvservice.entity.CandidateTest;
import by.kirilldikun.cvservice.exception.NotFoundException;
import by.kirilldikun.cvservice.mapper.CandidateTestMapper;
import by.kirilldikun.cvservice.repository.CandidateTestRepository;
import by.kirilldikun.cvservice.service.CandidateService;
import by.kirilldikun.cvservice.service.CandidateTestService;
import by.kirilldikun.cvservice.service.ResultService;
import by.kirilldikun.cvservice.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CandidateTestServiceImpl implements CandidateTestService {

    private final CandidateTestRepository candidateTestRepository;

    private final CandidateService candidateService;

    private final TestService testService;

    private final ResultService resultService;

    private final CandidateTestMapper candidateTestMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<CandidateTestDto> findAll(Pageable pageable) {
        return candidateTestRepository.findAll(pageable)
                .map(candidateTestMapper::toCandidateTestDto);
    }

    @Override
    @Transactional
    public CandidateTestDto save(CandidateTestDto candidateTestDto) {
        candidateService.findById(candidateTestDto.getCandidateId());
        testService.findById(candidateTestDto.getTestId());
        Long id = candidateTestDto.getId();
        if (Objects.isNull(id)) {
            return saveWithoutCheck(candidateTestDto);
        }
        if (!candidateTestRepository.existsById(id)) {
            throw new NotFoundException("Candidate test with id: %d not found".formatted(id));
        }
        resultService.deleteAllByCandidateTestId(id);
        return saveWithoutCheck(candidateTestDto);
    }

    @Transactional
    public CandidateTestDto saveWithoutCheck(CandidateTestDto candidateTestDto) {
        CandidateTest candidateTest = candidateTestMapper.toCandidateTest(candidateTestDto);
        candidateTestRepository.save(candidateTest);
        CandidateTestDto savedCandidateTestDto = candidateTestMapper.toCandidateTestDto(candidateTest);
        candidateTestDto.getResults()
                .forEach(resultDto -> resultDto.setCandidateTestId(savedCandidateTestDto.getId()));
        List<ResultDto> resultDtos = resultService.saveAll(candidateTestDto.getResults());
        savedCandidateTestDto.setResults(resultDtos);
        return savedCandidateTestDto;
    }
}
