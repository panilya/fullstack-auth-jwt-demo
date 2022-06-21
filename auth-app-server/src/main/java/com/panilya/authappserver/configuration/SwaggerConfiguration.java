package com.panilya.authappserver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.util.Predicates;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.negate(regex("/error")))
                .build()
                .apiInfo(metadata())
                .useDefaultResponseMessages(false)
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()))
                .tags(new Tag("auth", "Operations about authentication"))
                .genericModelSubstitutes(Optional.class);
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("JSON Web Token Authentication API")
                .description("This is a demonstration of JWT authentication service. You can find out more about JWT at [https://jwt.io/](https://jwt.io/). For testing API you should authorize. Once you have successfully logged in and obtained the token, you should click on the right top button `Authorize` and introduce it with the prefix \\\"Bearer \\\".")
                .version("1.1")
                .contact(new Contact(null, null, "panilyau@gmail.com"))
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("Authorization", authorizationScopes));
    }
}
