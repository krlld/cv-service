package by.kirilldikun.cvservice.controller;

import by.kirilldikun.cvservice.dto.TestDto;
import by.kirilldikun.cvservice.service.TestService;
import by.kirilldikun.cvservice.util.OffsetLimitPageable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/tests")
    @ResponseStatus(HttpStatus.OK)
    public Page<TestDto> findAll(
            @RequestParam(defaultValue = "") String query,
            @RequestParam(defaultValue = "0") @Min(0) Long offset,
            @RequestParam(defaultValue = "10") @Range(min = 1, max = 100) Integer limit) {
        OffsetLimitPageable offsetLimitPageable = OffsetLimitPageable.of(offset, limit);
        return testService.findAll(query, offsetLimitPageable);
    }

    @PostMapping("/tests")
    @ResponseStatus(HttpStatus.CREATED)
    public TestDto save(@Valid @RequestBody TestDto testDto) {
        return testService.save(testDto);
    }

    @PutMapping("/tests/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TestDto update(@PathVariable Long id, @Valid @RequestBody TestDto testDto) {
        testDto.setId(id);
        return testService.save(testDto);
    }
}
