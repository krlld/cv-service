package by.kirilldikun.cvservice.controller;

import by.kirilldikun.cvservice.dto.DirectionDto;
import by.kirilldikun.cvservice.service.DirectionService;
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
@Tag(name = "Direction controller", description = "Manages directions")
public class DirectionController {

    private final DirectionService directionService;

    @GetMapping("/directions")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "find all", description = "Gets all directions according to the specified parameters")
    public Page<DirectionDto> findAll(
            @Parameter(description = "Query for search") @RequestParam(defaultValue = "") String query,
            @Parameter(description = "Offset for pagination") @RequestParam(defaultValue = "0") @Min(0) Long offset,
            @Parameter(description = "Limit for pagination") @RequestParam(defaultValue = "10")
            @Range(min = 1, max = 100) Integer limit) {
        OffsetLimitPageable offsetLimitPageable = OffsetLimitPageable.of(offset, limit);
        return directionService.findAll(query, offsetLimitPageable);
    }

    @PostMapping("/directions")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "save direction", description = "Save direction and return it")
    public DirectionDto save(@Parameter(description = "Direction data") @Valid @RequestBody DirectionDto directionDto) {
        return directionService.save(directionDto);
    }

    @PutMapping("/directions/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "update direction", description = "Update direction and return it")
    public DirectionDto update(
            @Parameter(description = "Direction id") @PathVariable Long id,
            @Parameter(description = "Direction data") @Valid @RequestBody DirectionDto directionDto) {
        directionDto.setId(id);
        return directionService.save(directionDto);
    }
}
