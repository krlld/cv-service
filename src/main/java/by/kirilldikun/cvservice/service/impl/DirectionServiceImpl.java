package by.kirilldikun.cvservice.service.impl;

import by.kirilldikun.cvservice.dto.DirectionDto;
import by.kirilldikun.cvservice.entity.Direction;
import by.kirilldikun.cvservice.exception.NotFoundException;
import by.kirilldikun.cvservice.mapper.DirectionMapper;
import by.kirilldikun.cvservice.repository.DirectionRepository;
import by.kirilldikun.cvservice.service.DirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DirectionServiceImpl implements DirectionService {

    private final DirectionRepository directionRepository;

    private final DirectionMapper directionMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<DirectionDto> findAll(String query, Pageable pageable) {
        return directionRepository.findAllByNameContainsIgnoreCase(query, pageable)
                .map(directionMapper::toDirectionDto);
    }

    @Override
    @Transactional
    public DirectionDto save(DirectionDto directionDto) {
        Long id = directionDto.getId();
        if (Objects.isNull(id)) {
            return saveWithoutCheck(directionDto);
        }
        if (!directionRepository.existsById(id)) {
            throw new NotFoundException("Direction with id: %d not found".formatted(id));
        }
        return saveWithoutCheck(directionDto);
    }

    @Transactional
    public DirectionDto saveWithoutCheck(DirectionDto directionDto) {
        Direction direction = directionMapper.toDirection(directionDto);
        directionRepository.save(direction);
        return directionMapper.toDirectionDto(direction);
    }
}
