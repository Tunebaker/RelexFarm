package com.tunebaker.farm.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info =@Info(
                title = "Relex Farm user API",
                version = "${api.version}",
                contact = @Contact(
                        name = "Andrey (developer)", email = "user-apis@tunebaker.com"
                )
        ),
        servers = @Server(
                url = "http://localhost:${server.port}",
                description = "local"
        )
)
public class OpenAPISecurityConfiguration {
}
