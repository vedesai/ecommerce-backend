// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Crest & Thread E-commerce API")
                        .version("1.0.0")
                        .description("REST API for Crest & Thread e-commerce platform")
                        .contact(new Contact()
                                .name("Crest & Thread")
                                .email("support@crestandthread.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
// AI Generated Code by Deloitte + Cursor (END)