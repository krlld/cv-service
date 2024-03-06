package by.kirilldikun.cvservice.mapper;

import by.kirilldikun.cvservice.dto.ResultDto;
import by.kirilldikun.cvservice.entity.Result;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ResultMapper {

    @Mapping(target = "candidateTestId", source = "candidateTest.id")
    ResultDto toResultDto(Result result);

    List<ResultDto> toResultDtos(List<Result> results);

    @Mapping(target = "candidateTest.id", source = "candidateTestId")
    Result toResult(ResultDto resultDto);

    List<Result> toResults(List<ResultDto> resultDtos);
}
