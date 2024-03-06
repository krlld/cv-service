package by.kirilldikun.cvservice.service;

import by.kirilldikun.cvservice.dto.ResultDto;

import java.util.List;

public interface ResultService {

    List<ResultDto> saveAll(List<ResultDto> resultDtos);

    void deleteAllByCandidateTestId(Long candidateTestId);
}
