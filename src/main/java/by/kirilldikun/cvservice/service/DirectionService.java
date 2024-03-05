package by.kirilldikun.cvservice.service;

import by.kirilldikun.cvservice.dto.DirectionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DirectionService {

    Page<DirectionDto> findAll(String query, Pageable pageable);

    DirectionDto save(DirectionDto directionDto);
}
