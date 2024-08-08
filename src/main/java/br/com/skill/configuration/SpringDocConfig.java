package br.com.skill.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
	
@Configuration
public class SpringDocConfig {
	
	@Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Projeto Sistema de Skills")
                        .description("Documentação da API da aplicação Sistema de Skills."));
    }
	
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
            .group("public")
            .packagesToScan("br.com.skill")
            .pathsToMatch("/**")
            .build();
    }
}
