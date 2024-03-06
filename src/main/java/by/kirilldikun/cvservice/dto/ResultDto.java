package by.kirilldikun.cvservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ResultDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ZonedDateTime date;

    @NotNull
    private Integer mark;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long candidateTestId;
}
