package by.kirilldikun.cvservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Test data")
public class TestDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Test id", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Test name", example = "some name")
    private String name;

    @NotBlank
    @Schema(description = "Test description", example = "some description")
    private String description;

    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(description = "Direction ids", accessMode = Schema.AccessMode.WRITE_ONLY, example = "1")
    private List<Long> directionIds;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Direction names", accessMode = Schema.AccessMode.READ_ONLY, example = "Java")
    private List<String> directionNames;
}
