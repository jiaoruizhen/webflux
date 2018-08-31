package com.dognessnetwork.webflux.controller;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.alibaba.fastjson.JSONObject;
import com.dognessnetwork.webflux.domain.User;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
    public class TimeHandler {
        public Mono<ServerResponse> getTime(ServerRequest serverRequest) {
            return  ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("Now is " + new SimpleDateFormat("HH:mm:ss").format(new Date())), String.class);
        }
        public Mono<ServerResponse> getDate(ServerRequest serverRequest) {
            return  ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("Today is " + new SimpleDateFormat("yyyy-MM-dd").format(new Date())), String.class);
        }
        
        public Mono<ServerResponse> sendTimePerSec(ServerRequest serverRequest) {
            return ok().contentType(MediaType.TEXT_EVENT_STREAM).body(  // 1
                    Flux.interval(Duration.ofSeconds(1)).   // 2
                            map(l -> new SimpleDateFormat("HH:mm:ss").format(new Date())), 
                    String.class);
        }
        
		public Mono<ServerResponse> getUser(ServerRequest serverRequest){
			System.out.println(serverRequest.queryParams());
        	User user=new User();
        	user.setAge(serverRequest.queryParam("age").get().toString());
        	user.setName(serverRequest.queryParam("name").get().toString());
        	user.setUsername(serverRequest.queryParam("username").get().toString());
        	
        	return ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(JSONObject.toJSONString(new Response(200,"成功",user))), String.class);
        }
    }