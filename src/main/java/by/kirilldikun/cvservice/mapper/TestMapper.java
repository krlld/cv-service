package by.kirilldikun.cvservice.mapper;

import by.kirilldikun.cvservice.dto.TestDto;
import by.kirilldikun.cvservice.entity.Direction;
import by.kirilldikun.cvservice.entity.Test;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TestMapper {

    @Mapping(target = "directionIds", source = "directions")
    @Mapping(target = "directionNames", source = "directions")
    TestDto toTestDto(Test test);

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
    Test toTest(TestDto testDto);

    default List<Direction> mapToDirections(List<Long> directionIds) {
        return directionIds
                .stream()
                .map(directionId -> new Direction().setId(directionId))
                .toList();
    }
}

