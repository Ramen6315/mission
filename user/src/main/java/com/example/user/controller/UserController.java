package com.example.user.controller;


import com.example.user.provider.JwtProvider;
import com.example.user.vo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final JwtProvider jwtProvider;

    @GetMapping("/user/{url}")
    public String controller(@PathVariable String url) {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User("u1", "testUser");

        String userInfo = null;
        try {
            userInfo = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            log.error("error", e);
            e.printStackTrace();
        }

        String result;

        switch (url) {
            case "product":
                result = createApiRequest("http://localhost:8080/",userInfo, url);
                break;
            case "purchase":
                result = createApiRequest("http://localhost:8082/",userInfo, url);
                break;
            default:
                throw new UnsupportedOperationException("제공하지 않는 Api입니다");
        }
        return result;
    }

    private String createApiRequest(String host, String userInfo, String url) {
        String apiUrl = host + url;
        HttpHeaders httpHeaders = new HttpHeaders();
        String token = jwtProvider.createToken(userInfo);

        httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
        return result.getBody();
    }

}
