package by.kirilldikun.cvservice.controller;

import by.kirilldikun.cvservice.dto.DirectionDto;
import by.kirilldikun.cvservice.service.DirectionService;
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
public class DirectionController {

    private final DirectionService directionService;

    @GetMapping("/directions")
    @ResponseStatus(HttpStatus.OK)
    public Page<DirectionDto> findAll(
            @RequestParam(defaultValue = "") String query,
            @RequestParam(defaultValue = "0") @Min(0) Long offset,
            @RequestParam(defaultValue = "10") @Range(min = 1, max = 100) Integer limit) {
        OffsetLimitPageable offsetLimitPageable = OffsetLimitPageable.of(offset, limit);
        return directionService.findAll(query, offsetLimitPageable);
    }

    @PostMapping("/directions")
    @ResponseStatus(HttpStatus.CREATED)
    public DirectionDto save(@Valid @RequestBody DirectionDto directionDto) {
        return directionService.save(directionDto);
    }

    @PutMapping("/directions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DirectionDto update(@PathVariable Long id, @Valid @RequestBody DirectionDto directionDto) {
        directionDto.setId(id);
        return directionService.save(directionDto);
    }
}
