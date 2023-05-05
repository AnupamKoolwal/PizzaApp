package com.example.SpringAPIGateway.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/api/v1/**")
                                .uri("http://localhost:8086")
//                        .uri("http://user-auth-service1:8086")
                        )
                .route(p -> p
                        .path("/api/v2/**")
//                        .uri("http://user-pizza-service1:8083")
                                .uri("http://localhost:8083")
                        )
                .build();
    }
}
