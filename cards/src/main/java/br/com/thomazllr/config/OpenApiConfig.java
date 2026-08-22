package br.com.thomazllr.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI cardsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cards API")
                        .description("Documentação da API de gerenciamento de cartões.")
                        .version("v1")
                        .contact(new Contact()
                                .name("thomazllr")
                                .email("thomazllrdev@gmail.com")));
    }
}
