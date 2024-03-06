package by.kirilldikun.cvservice.service.impl;

import by.kirilldikun.cvservice.dto.ResultDto;
import by.kirilldikun.cvservice.entity.Result;
import by.kirilldikun.cvservice.mapper.ResultMapper;
import by.kirilldikun.cvservice.repository.ResultRepository;
import by.kirilldikun.cvservice.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;

    private final ResultMapper resultMapper;

    @Override
    @Transactional
    public List<ResultDto> saveAll(List<ResultDto> resultDtos) {
        resultDtos.forEach(resultDto -> resultDto.setDate(ZonedDateTime.now()));
        List<Result> results = resultMapper.toResults(resultDtos);
        resultRepository.saveAll(results);
        return resultMapper.toResultDtos(results);
    }

    @Override
    @Transactional
    public void deleteAllByCandidateTestId(Long candidateTestId) {
        resultRepository.deleteAllByCandidateTestId(candidateTestId);
    }
}
