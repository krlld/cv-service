package by.kirilldikun.cvservice.controller;

import by.kirilldikun.cvservice.dto.CandidateDto;
import by.kirilldikun.cvservice.dto.FileInfoDto;
import by.kirilldikun.cvservice.service.CandidateService;
import by.kirilldikun.cvservice.util.OffsetLimitPageable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "Candidate controller", description = "Manages candidates")
public class CandidateController {

    private final CandidateService candidateService;

    @GetMapping("/candidates")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "find all", description = "Gets all candidates according to the specified parameters")
    public Page<CandidateDto> findAll(
            @Parameter(description = "Query for search") @RequestParam(defaultValue = "") String query,
            @Parameter(description = "Offset for pagination") @RequestParam(defaultValue = "0") @Min(0) Long offset,
            @Parameter(description = "Limit for pagination") @RequestParam(defaultValue = "10")
            @Range(min = 1, max = 100) Integer limit) {
        OffsetLimitPageable offsetLimitPageable = OffsetLimitPageable.of(offset, limit);
        return candidateService.findAll(query, offsetLimitPageable);
    }

    @GetMapping(value = "/candidates/{id}/photo", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Operation(summary = "download photo", description = "Download photo by candidate id")
    public ResponseEntity<Resource> downloadPhoto(@Parameter(description = "Candidate id") @PathVariable Long id) {
        FileInfoDto fileInfoDto = candidateService.findPhotoByCandidateId(id);
        String encodedFilename = UriUtils.encode(fileInfoDto.getName(), StandardCharsets.UTF_8);
        Resource resource = new ByteArrayResource(fileInfoDto.getBody());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFilename)
                .body(resource);
    }

    @GetMapping(value = "/candidates/{id}/cv", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Operation(summary = "download cv", description = "Download cv by candidate id")
    public ResponseEntity<Resource> downloadCv(@Parameter(description = "Candidate id") @PathVariable Long id) {
        FileInfoDto fileInfoDto = candidateService.findCvByCandidateId(id);
        Resource resource = new ByteArrayResource(fileInfoDto.getBody());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + fileInfoDto.getName())
                .body(resource);
    }

    @PostMapping("/candidates")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "save candidate", description = "Save candidate and return it")
    public CandidateDto save(
            @Parameter(description = "Candidate photo") @RequestPart MultipartFile photo,
            @Parameter(description = "Candidate cv") @RequestPart MultipartFile cv,
            @Parameter(description = "Candidate data") @Valid CandidateDto candidateDto) {
        candidateDto.setPhotoFile(photo);
        candidateDto.setCvFile(cv);
        return candidateService.save(candidateDto);
    }

    @PutMapping("/candidates/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "update candidate", description = "Update candidate and return it")
    public CandidateDto update(
            @Parameter(description = "Candidate id") @PathVariable Long id,
            @Parameter(description = "Candidate photo") @RequestPart MultipartFile photo,
            @Parameter(description = "Candidate cv") @RequestPart MultipartFile cv,
            @Parameter(description = "Candidate data") @Valid CandidateDto candidateDto) {
        candidateDto.setId(id);
        candidateDto.setPhotoFile(photo);
        candidateDto.setCvFile(cv);
        return candidateService.save(candidateDto);
    }
}
