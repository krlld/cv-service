package by.kirilldikun.cvservice.service;

import by.kirilldikun.cvservice.dto.TestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TestService {

    Page<TestDto> findAll(String query, Pageable pageable);

    TestDto findById(Long id);

    TestDto save(TestDto testDto);
}
