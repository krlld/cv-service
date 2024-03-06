package by.kirilldikun.cvservice.mapper;

import by.kirilldikun.cvservice.dto.FileInfoDto;
import by.kirilldikun.cvservice.entity.FileInfo;
import org.mapstruct.Mapper;

@Mapper
public interface FileInfoMapper {

    FileInfoDto toFileInfoDto(FileInfo fileInfo);

    FileInfo toFileInfo(FileInfoDto fileInfoDto);
}
