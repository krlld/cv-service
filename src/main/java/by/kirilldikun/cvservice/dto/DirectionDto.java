package by.kirilldikun.cvservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Direction data")
public class DirectionDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Direction id", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Direction name", example = "Java")
    private String name;

    @NotBlank
    @Schema(description = "Direction description", example = "Cool language")
    private String description;
}
