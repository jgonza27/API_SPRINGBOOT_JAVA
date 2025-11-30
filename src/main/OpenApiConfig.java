package soy.profesor.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API REST - Gestión de Empleados", description = "API REST para gestión de empleados y departamentos", version = "1.0", contact = @Contact(name = "Juan", email = "tu-email@ejemplo.com"), license = @License(name = "CC BY")))
public class OpenApiConfig {

}