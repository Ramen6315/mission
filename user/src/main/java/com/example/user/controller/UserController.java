package com.example.user.controller;


import com.example.user.provider.JwtProvider;
import com.example.user.vo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final JwtProvider jwtProvider;

    @GetMapping("/user/{url}")
    public String controller(@PathVariable String url) {
        return "hello";
    }
}
