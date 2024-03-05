package by.kirilldikun.cvservice.mapper;

import by.kirilldikun.cvservice.dto.DirectionDto;
import by.kirilldikun.cvservice.entity.Direction;
import org.mapstruct.Mapper;

@Mapper
public interface DirectionMapper {

    DirectionDto toDirectionDto(Direction direction);

    Direction toDirection(DirectionDto directionDto);
}
