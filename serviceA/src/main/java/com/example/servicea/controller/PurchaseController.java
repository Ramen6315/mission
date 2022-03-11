package com.example.servicea.controller;

import com.example.servicea.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PurchaseController {
    private final JwtProvider jwtProvider;

    @GetMapping("/purchase")
    public String addPurchase(String key, HttpEntity<Map<String, String>> entity) {
        HttpHeaders headers = entity.getHeaders();

        List<String> authorization = headers.get("authorization");
        String token = authorization.get(0);
        return jwtProvider.getSubject(token);
    }
}
