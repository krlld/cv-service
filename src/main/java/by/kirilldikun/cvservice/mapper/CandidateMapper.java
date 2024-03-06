package by.kirilldikun.cvservice.mapper;

import by.kirilldikun.cvservice.dto.CandidateDto;
import by.kirilldikun.cvservice.entity.Candidate;
import by.kirilldikun.cvservice.entity.Direction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CandidateMapper {

    @Mapping(target = "directionIds", source = "directions")
    @Mapping(target = "directionNames", source = "directions")
    CandidateDto toCandidateDto(Candidate candidate);

    default List<Long> mapToIds(List<Direction> directions) {
        return directions
                .stream()
                .map(Direction::getId)
                .toList();
    }

    default List<String> mapToNames(List<Direction> directions) {
        return directions
                .stream()
                .map(Direction::getName)
                .toList();
    }

    @Mapping(target = "directions", source = "directionIds")
    Candidate toCandidate(CandidateDto candidateDto);

    default List<Direction> mapToDirections(List<Long> directionIds) {
        return directionIds
                .stream()
                .map(directionId -> new Direction().setId(directionId))
                .toList();
    }
}
