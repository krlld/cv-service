package by.kirilldikun.cvservice.service;

import by.kirilldikun.cvservice.dto.FileInfoDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileInfoDto findById(Long id);

    FileInfoDto save(MultipartFile file);

    void delete(Long id);
}
