package by.kirilldikun.cvservice.controller;

import by.kirilldikun.cvservice.dto.CandidateDto;
import by.kirilldikun.cvservice.dto.FileInfoDto;
import by.kirilldikun.cvservice.service.CandidateService;
import by.kirilldikun.cvservice.util.OffsetLimitPageable;
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
public class CandidateController {

    private final CandidateService candidateService;

    @GetMapping("/candidates")
    @ResponseStatus(HttpStatus.OK)
    public Page<CandidateDto> findAll(
            @RequestParam(defaultValue = "") String query,
            @RequestParam(defaultValue = "0") @Min(0) Long offset,
            @RequestParam(defaultValue = "10") @Range(min = 1, max = 100) Integer limit) {
        OffsetLimitPageable offsetLimitPageable = OffsetLimitPageable.of(offset, limit);
        return candidateService.findAll(query, offsetLimitPageable);
    }

    @GetMapping(value = "/candidates/{id}/photo", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> downloadPhoto(@PathVariable Long id) {
        FileInfoDto fileInfoDto = candidateService.findPhotoByCandidateId(id);
        String encodedFilename = UriUtils.encode(fileInfoDto.getName(), StandardCharsets.UTF_8);
        Resource resource = new ByteArrayResource(fileInfoDto.getBody());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFilename)
                .body(resource);
    }

    @GetMapping(value = "/candidates/{id}/cv", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> downloadCv(@PathVariable Long id) {
        FileInfoDto fileInfoDto = candidateService.findCvByCandidateId(id);
        Resource resource = new ByteArrayResource(fileInfoDto.getBody());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + fileInfoDto.getName())
                .body(resource);
    }

    @PostMapping("/candidates")
    @ResponseStatus(HttpStatus.CREATED)
    public CandidateDto save(
            @RequestPart MultipartFile photo,
            @RequestPart MultipartFile cv,
            @Valid CandidateDto candidateDto) {
        candidateDto.setPhotoFile(photo);
        candidateDto.setCvFile(cv);
        return candidateService.save(candidateDto);
    }

    @PutMapping("/candidates/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CandidateDto update(
            @PathVariable Long id,
            @RequestPart MultipartFile photo,
            @RequestPart MultipartFile cv,
            @Valid CandidateDto candidateDto) {
        candidateDto.setId(id);
        candidateDto.setPhotoFile(photo);
        candidateDto.setCvFile(cv);
        return candidateService.save(candidateDto);
    }
}
