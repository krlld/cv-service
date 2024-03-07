package by.kirilldikun.cvservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Candidate test data")
public class CandidateTestDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Candidate test id", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @NotNull
    @Schema(description = "Candidate id", example = "1")
    private Long candidateId;

    @NotNull
    @Schema(description = "Test id", example = "1")
    private Long testId;

    @NotEmpty
    @Schema(description = "Results")
    private List<ResultDto> results;
}
