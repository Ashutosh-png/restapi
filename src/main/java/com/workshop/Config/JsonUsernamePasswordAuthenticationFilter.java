package com.workshop.Config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.hibernate.mapping.Map;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final ObjectMapper objectMapper;
    private final ThreadLocal<String> requestContent = new ThreadLocal<>();

    public JsonUsernamePasswordAuthenticationFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        try {
            String body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
            this.requestContent.set(body);
            return objectMapper.readTree(body).get("username").asText();
        } catch (IOException e) {
            throw new AuthenticationServiceException("Could not process the given request.");
        }
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        try {
            return objectMapper.readTree(requestContent.get()).get("password").asText();
        } catch (JsonProcessingException e) {
            throw new AuthenticationServiceException("Could not process the given request.");
        }
    }
}