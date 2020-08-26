package za.co.discovery.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String WEBSITE_URL = "https://www.dvt.co.za/";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("za.co.discovery.assignment"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {

        final Contact contact = new Contact("Jaco Van Der Merwe", WEBSITE_URL, "jvandermerwe@jhb.dvt.co.za");

        return new ApiInfo(
                "Interstellar Transport System",
                "Interstellar Transport System",
                "1.0.0", WEBSITE_URL, contact,
                "License of API", WEBSITE_URL, Collections.emptyList());
    }
}
