package by.kirilldikun.cvservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "File info data")
public class FileInfoDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Candidate id", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "File name", example = "some name")
    private String name;

    @Min(0)
    @Schema(description = "File size", example = "10000")
    private Long size;

    @JsonIgnore
    private byte[] body;
}
