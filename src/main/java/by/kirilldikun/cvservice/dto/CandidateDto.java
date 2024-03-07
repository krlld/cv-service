package by.kirilldikun.cvservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Candidate data")
public class CandidateDto {

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Schema(description = "Candidate id", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Candidate second name", example = "second name")
    private String secondName;

    @NotBlank
    @Schema(description = "Candidate first name", example = "first name")
    private String firstName;

    @NotBlank
    @Schema(description = "Candidate patronymic", example = "patronymic")
    private String patronymic;

    @JsonIgnore
    private MultipartFile photoFile;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Schema(description = "Candidate photo file id", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long photoFileId;

    @NotBlank
    @Schema(description = "Candidate description", example = "description")
    private String description;

    @JsonIgnore
    private MultipartFile cvFile;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Schema(description = "Candidate cv file id", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long cvFileId;

    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(description = "Candidate direction ids", accessMode = Schema.AccessMode.WRITE_ONLY, example = "1")
    private List<Long> directionIds;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Candidate direction names", accessMode = Schema.AccessMode.READ_ONLY, example = "Java")
    private List<String> directionNames;
}
