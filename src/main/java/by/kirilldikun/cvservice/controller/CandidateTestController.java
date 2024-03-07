package by.kirilldikun.cvservice.controller;

import by.kirilldikun.cvservice.dto.CandidateTestDto;
import by.kirilldikun.cvservice.service.CandidateTestService;
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
@Tag(name = "Candidate test controller", description = "Manages candidate tests")
public class CandidateTestController {

    private final CandidateTestService candidateTestService;

    @GetMapping("/candidate-tests")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "find all", description = "Gets all candidate tests according to the specified parameters")
    public Page<CandidateTestDto> findAll(
            @Parameter(description = "Offset for pagination") @RequestParam(defaultValue = "0") @Min(0) Long offset,
            @Parameter(description = "Limit for pagination") @RequestParam(defaultValue = "10")
            @Range(min = 1, max = 100) Integer limit) {
        OffsetLimitPageable offsetLimitPageable = OffsetLimitPageable.of(offset, limit);
        return candidateTestService.findAll(offsetLimitPageable);
    }

    @PostMapping("/candidate-tests")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "save candidate test", description = "Save candidate test and return it")
    public CandidateTestDto save(
            @Parameter(description = "Candidate test data") @Valid @RequestBody CandidateTestDto candidateTestDto) {
        return candidateTestService.save(candidateTestDto);
    }

    @PutMapping("/candidate-tests/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "update candidate test", description = "Update candidate test and return it")
    public CandidateTestDto update(
            @Parameter(description = "Candidate test id") @PathVariable Long id,
            @Parameter(description = "Candidate test data") @Valid @RequestBody CandidateTestDto candidateTestDto) {
        candidateTestDto.setId(id);
        return candidateTestService.save(candidateTestDto);
    }
}
