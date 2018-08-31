package com.dognessnetwork.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
@Configuration
    public class RouterConfig {
        @Autowired
        private TimeHandler timeHandler;
        
        @Autowired
        private UserHandler userHandler;
        @Bean
        public RouterFunction<ServerResponse> timerRouter() {
            return route(GET("/time"), timeHandler::getTime)
                    .andRoute(GET("/date"), timeHandler::getDate)
                    .andRoute(GET("/times"), timeHandler::sendTimePerSec)
                    .andRoute(POST("/getUser"),userHandler::getUser);  // 增加这一行
        }
    }