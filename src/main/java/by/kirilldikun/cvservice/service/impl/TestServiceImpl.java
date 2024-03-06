package by.kirilldikun.cvservice.service.impl;

import by.kirilldikun.cvservice.dto.DirectionDto;
import by.kirilldikun.cvservice.dto.TestDto;
import by.kirilldikun.cvservice.entity.Test;
import by.kirilldikun.cvservice.exception.NotFoundException;
import by.kirilldikun.cvservice.mapper.TestMapper;
import by.kirilldikun.cvservice.repository.TestRepository;
import by.kirilldikun.cvservice.service.DirectionService;
import by.kirilldikun.cvservice.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    private final DirectionService directionService;

    private final TestMapper testMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<TestDto> findAll(String query, Pageable pageable) {
        return testRepository.findAllByNameContainsIgnoreCase(query, pageable)
                .map(testMapper::toTestDto);
    }

    @Override
    @Transactional(readOnly = true)
    public TestDto findById(Long id) {
        return testRepository.findById(id)
                .map(testMapper::toTestDto)
                .orElseThrow(() -> new NotFoundException("Test with id: %d not found".formatted(id)));
    }

    @Override
    @Transactional
    public TestDto save(TestDto testDto) {
        List<DirectionDto> directionDtos = directionService.findAllByIds(testDto.getDirectionIds());
        if (directionDtos.size() != testDto.getDirectionIds().size()) {
            throw new NotFoundException("Some direction not found");
        }
        Long id = testDto.getId();
        if (Objects.isNull(id)) {
            return saveWithoutCheck(testDto);
        }
        if (!testRepository.existsById(id)) {
            throw new NotFoundException("Test with id: %d not found".formatted(id));
        }
        return saveWithoutCheck(testDto);
    }

    @Transactional
    public TestDto saveWithoutCheck(TestDto testDto) {
        Test test = testMapper.toTest(testDto);
        testRepository.save(test);
        return testMapper.toTestDto(test);
    }
}
