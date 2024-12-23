package teamchallenge.server.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi getAdminApiGroup() {
        return GroupedOpenApi.builder()
                .group("Admin api")
                .pathsToMatch("/api/admin/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getOpenApiGroup() {
        return GroupedOpenApi.builder()
                .group("Open api")
                .pathsToMatch("/api/open/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getAuthApiGroup() {
        return GroupedOpenApi.builder()
                .group("Authentication")
                .pathsToMatch("/api/open/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getCartApiGroup() {
        return GroupedOpenApi.builder()
                .group("Cart")
                .pathsToMatch("/api/open/cart/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getBookApiGroup() {
        return GroupedOpenApi.builder()
                .group("Book")
                .pathsToMatch("/api/open/book/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getUserApiGroup() {
        return GroupedOpenApi.builder()
                .group("User")
                .pathsToMatch("/api/open/user/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getCategoryApiGroup() {
        return GroupedOpenApi.builder()
                .group("Category")
                .pathsToMatch("/api/open/category/**")
                .build();
    }
}
