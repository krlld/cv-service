package by.kirilldikun.cvservice.mapper;

import by.kirilldikun.cvservice.dto.CandidateTestDto;
import by.kirilldikun.cvservice.entity.CandidateTest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = ResultMapper.class)
public interface CandidateTestMapper {

    @Mapping(target = "candidateId", source = "candidate.id")
    @Mapping(target = "testId", source = "test.id")
    CandidateTestDto toCandidateTestDto(CandidateTest candidateTest);

    @Mapping(target = "candidate.id", source = "candidateId")
    @Mapping(target = "test.id", source = "testId")
    CandidateTest toCandidateTest(CandidateTestDto candidateTestDto);
}
