package com.xiaoxiong.webflux.router;

import com.xiaoxiong.webflux.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/11  12:51
 */
@Configuration
public class AllRouter {

    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandler userHandler) {
        return nest(path("/userRouter"),
                route(GET("/"), userHandler::getAllUser)
                .andRoute(POST("/").and(accept(APPLICATION_JSON)), userHandler::createUser)
                .andRoute(PUT("/").and(accept(APPLICATION_JSON)), userHandler::updateUser)
                .andRoute(DELETE("/{id}"), userHandler::deleteUser));
    }

}
