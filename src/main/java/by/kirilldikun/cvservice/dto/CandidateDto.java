package by.kirilldikun.cvservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDto {

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Long id;

    @NotBlank
    private String secondName;

    @NotBlank
    private String firstName;

    @NotBlank
    private String patronymic;

    @JsonIgnore
    private MultipartFile photoFile;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Long photoFileId;

    @NotBlank
    private String description;

    @JsonIgnore
    private MultipartFile cvFile;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Long cvFileId;

    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Long> directionIds;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<String> directionNames;
}
