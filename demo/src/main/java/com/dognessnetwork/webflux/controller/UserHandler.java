package com.dognessnetwork.webflux.controller;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.alibaba.fastjson.JSONObject;
import com.dognessnetwork.webflux.domain.User;

import reactor.core.publisher.Mono;

@Component
public class UserHandler {
	 
	public Mono<ServerResponse> getUser(ServerRequest serverRequest){
    	User user=new User();
    	user.setAge(serverRequest.queryParam("age").get().toString());
    	user.setName(serverRequest.queryParam("name").get().toString());
    	user.setUsername(serverRequest.queryParam("username").get().toString());
    	
    	return ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(JSONObject.toJSONString(new Response(200,"成功",user))), String.class);
    }
}
