package com.enigma.mevents.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
    info = @Info(
            contact = @Contact(
                    name = "Enigma Code",
                    email = "contact@enigmacode.cm",
                    url = "https://enigmacode.cm/career"
            ),
            description = "Documentation of m-event api",
            title = "m-event api specification - Enigma",
            version = "1.0",
            license = @License(
                    name = "MIT - Licence"
            ),
            termsOfService = "Free for use under MIT - licence rules"
    ),
        servers = {
            @Server(
                    description = "Local ENV",
                    url = "http://localhost:8080"
            )
        },
        security = {
            @SecurityRequirement(
                    name = "bearerAuth"
            )
        }

)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT authentication",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {



}
