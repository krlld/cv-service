package by.kirilldikun.cvservice.service;

import by.kirilldikun.cvservice.dto.DirectionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DirectionService {

    Page<DirectionDto> findAll(String query, Pageable pageable);

    List<DirectionDto> findAllByIds(List<Long> ids);

    DirectionDto save(DirectionDto directionDto);
}
