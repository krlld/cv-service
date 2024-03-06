package by.kirilldikun.cvservice.controller;

import by.kirilldikun.cvservice.dto.CandidateTestDto;
import by.kirilldikun.cvservice.service.CandidateTestService;
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
public class CandidateTestController {

    private final CandidateTestService candidateTestService;

    @GetMapping("/candidate-tests")
    @ResponseStatus(HttpStatus.OK)
    public Page<CandidateTestDto> findAll(
            @RequestParam(defaultValue = "0") @Min(0) Long offset,
            @RequestParam(defaultValue = "10") @Range(min = 1, max = 100) Integer limit) {
        OffsetLimitPageable offsetLimitPageable = OffsetLimitPageable.of(offset, limit);
        return candidateTestService.findAll(offsetLimitPageable);
    }

    @PostMapping("/candidate-tests")
    @ResponseStatus(HttpStatus.CREATED)
    public CandidateTestDto save(@Valid @RequestBody CandidateTestDto candidateTestDto) {
        return candidateTestService.save(candidateTestDto);
    }

    @PutMapping("/candidate-tests/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CandidateTestDto update(@PathVariable Long id, @Valid @RequestBody CandidateTestDto candidateTestDto) {
        candidateTestDto.setId(id);
        return candidateTestService.save(candidateTestDto);
    }
}
