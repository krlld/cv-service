package by.kirilldikun.cvservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Result data")
public class ResultDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Result id", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Result date", example = "2024-03-06T23:11:54.949335Z")
    private ZonedDateTime date;

    @NotNull
    @Schema(description = "Mark", example = "10")
    private Integer mark;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Candidate test id", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long candidateTestId;
}
