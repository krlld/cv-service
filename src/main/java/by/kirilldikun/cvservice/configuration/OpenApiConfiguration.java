package by.kirilldikun.cvservice.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "CV service Api",
                description = "CV service system", version = "0.0.1-SNAPSHOT",
                contact = @Contact(
                        name = "Kirill Dikun",
                        email = "kirilldikun25@gmail.com"
                )
        )
)
public class OpenApiConfiguration {

}
