package by.kirilldikun.cvservice.service.impl;

import by.kirilldikun.cvservice.dto.FileInfoDto;
import by.kirilldikun.cvservice.entity.FileInfo;
import by.kirilldikun.cvservice.exception.NotFoundException;
import by.kirilldikun.cvservice.mapper.FileInfoMapper;
import by.kirilldikun.cvservice.repository.FileInfoRepository;
import by.kirilldikun.cvservice.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileInfoRepository fileInfoRepository;

    private final FileInfoMapper fileInfoMapper;

    @Override
    @Transactional(readOnly = true)
    public FileInfoDto findById(Long id) {
        return fileInfoRepository.findById(id)
                .map(fileInfoMapper::toFileInfoDto)
                .orElseThrow(() -> new NotFoundException("File with id: %d not found"));
    }

    @Override
    @Transactional
    public FileInfoDto save(MultipartFile multipartFile) {
        FileInfo fileInfo;
        try {
            fileInfo = new FileInfo()
                    .setName(multipartFile.getOriginalFilename())
                    .setSize(multipartFile.getSize())
                    .setBody(multipartFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileInfoRepository.save(fileInfo);
        return fileInfoMapper.toFileInfoDto(fileInfo);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        fileInfoRepository.deleteById(id);
    }
}
