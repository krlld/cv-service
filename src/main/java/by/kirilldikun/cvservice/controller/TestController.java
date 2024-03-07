package by.kirilldikun.cvservice.controller;

import by.kirilldikun.cvservice.dto.TestDto;
import by.kirilldikun.cvservice.service.TestService;
import by.kirilldikun.cvservice.util.OffsetLimitPageable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Test controller", description = "Manages tests")
public class TestController {

    private final TestService testService;

    @GetMapping("/tests")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "find all", description = "Gets all tests according to the specified parameters")
    public Page<TestDto> findAll(
            @Parameter(description = "Query for search") @RequestParam(defaultValue = "") String query,
            @Parameter(description = "Offset for pagination") @RequestParam(defaultValue = "0") @Min(0) Long offset,
            @Parameter(description = "Limit for pagination") @RequestParam(defaultValue = "10")
            @Range(min = 1, max = 100) Integer limit) {
        OffsetLimitPageable offsetLimitPageable = OffsetLimitPageable.of(offset, limit);
        return testService.findAll(query, offsetLimitPageable);
    }

    @PostMapping("/tests")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "save test", description = "Save test and return it")
    public TestDto save(@Parameter(description = "Test data") @Valid @RequestBody TestDto testDto) {
        return testService.save(testDto);
    }

    @PutMapping("/tests/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "update test", description = "Update test and return it")
    public TestDto update(
            @Parameter(description = "Test id") @PathVariable Long id,
            @Parameter(description = "Test data") @Valid @RequestBody TestDto testDto) {
        testDto.setId(id);
        return testService.save(testDto);
    }
}
