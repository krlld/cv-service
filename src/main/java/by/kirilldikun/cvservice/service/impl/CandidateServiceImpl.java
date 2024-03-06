package by.kirilldikun.cvservice.service.impl;

import by.kirilldikun.cvservice.dto.CandidateDto;
import by.kirilldikun.cvservice.dto.DirectionDto;
import by.kirilldikun.cvservice.dto.FileInfoDto;
import by.kirilldikun.cvservice.entity.Candidate;
import by.kirilldikun.cvservice.exception.NotFoundException;
import by.kirilldikun.cvservice.mapper.CandidateMapper;
import by.kirilldikun.cvservice.repository.CandidateRepository;
import by.kirilldikun.cvservice.service.CandidateService;
import by.kirilldikun.cvservice.service.DirectionService;
import by.kirilldikun.cvservice.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    private final DirectionService directionService;

    private final FileService fileService;

    private final CandidateMapper candidateMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<CandidateDto> findAll(String query, Pageable pageable) {
        return candidateRepository.findAllByDescriptionContainsIgnoreCase(query, pageable)
                .map(candidateMapper::toCandidateDto);
    }

    @Override
    @Transactional(readOnly = true)
    public CandidateDto findById(Long id) {
        return candidateRepository.findById(id)
                .map(candidateMapper::toCandidateDto)
                .orElseThrow(() -> new NotFoundException("Candidate with id: %d not found".formatted(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public FileInfoDto findPhotoByCandidateId(Long candidateId) {
        CandidateDto candidateDto = findById(candidateId);
        return fileService.findById(candidateDto.getPhotoFileId());
    }

    @Override
    @Transactional(readOnly = true)
    public FileInfoDto findCvByCandidateId(Long candidateId) {
        CandidateDto candidateDto = findById(candidateId);
        return fileService.findById(candidateDto.getCvFileId());
    }

    @Override
    @Transactional
    public CandidateDto save(CandidateDto candidateDto) {
        List<DirectionDto> directionDtos = directionService.findAllByIds(candidateDto.getDirectionIds());
        if (directionDtos.size() != candidateDto.getDirectionIds().size()) {
            throw new NotFoundException("Some direction not found");
        }
        Long id = candidateDto.getId();
        if (Objects.isNull(id)) {
            return saveWithoutCheck(candidateDto);
        }
        if (!candidateRepository.existsById(id)) {
            throw new NotFoundException("Candidate with id: %d not found".formatted(id));
        }
        deleteCandidateFiles(id);
        return saveWithoutCheck(candidateDto);
    }

    @Transactional
    public CandidateDto saveWithoutCheck(CandidateDto candidateDto) {
        FileInfoDto photo = fileService.save(candidateDto.getPhotoFile());
        FileInfoDto cv = fileService.save(candidateDto.getCvFile());
        candidateDto.setPhotoFileId(photo.getId());
        candidateDto.setCvFileId(cv.getId());
        Candidate candidate = candidateMapper.toCandidate(candidateDto);
        candidateRepository.save(candidate);
        return candidateMapper.toCandidateDto(candidate);
    }

    @Transactional
    public void deleteCandidateFiles(Long id) {
        CandidateDto candidateDto = findById(id);
        fileService.delete(candidateDto.getPhotoFileId());
        fileService.delete(candidateDto.getCvFileId());
    }
}
