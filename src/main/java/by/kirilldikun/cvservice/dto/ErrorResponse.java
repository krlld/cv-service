package by.kirilldikun.cvservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Error response")
public class ErrorResponse {

    @Schema(description = "Error code", example = "RESOURCE_NOT_FOUND")
    private String code;

    @Schema(description = "Error message", example = "Direction with id: 1 not found")
    private String message;

    @Schema(description = "Field errors")
    private List<Field> fields;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Field {

        @Schema(description = "Field name")
        private String name;

        @Schema(description = "Error message")
        private String message;
    }

    public void addField(String name, String message) {
        if (fields == null) {
            fields = new ArrayList<>();
        }
        fields.add(new Field(name, message));
    }
}
