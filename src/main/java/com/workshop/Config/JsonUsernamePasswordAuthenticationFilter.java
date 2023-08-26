package com.workshop.Config;

import java.io.IOException;

import org.hibernate.mapping.Map;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        try {
            java.util.Map<String, String> jsonMap = objectMapper.readValue(request.getInputStream(), java.util.Map.class);
            return jsonMap.get("username");
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        try {
            java.util.Map<String, String> jsonMap = objectMapper.readValue(request.getInputStream(), java.util.Map.class);
            return jsonMap.get("password");
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        return super.attemptAuthentication(request, response);
    }

}
