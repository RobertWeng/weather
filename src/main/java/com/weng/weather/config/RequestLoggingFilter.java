package com.weng.weather.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@WebFilter("/*") // Apply the filter to all incoming requests
public class RequestLoggingFilter extends OncePerRequestFilter {

    public static final String REQUEST_UUID_KEY = "requestUUID";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String requestUUID = UUID.randomUUID().toString();

        // Put the UUID in MDC
        MDC.put(REQUEST_UUID_KEY, requestUUID);

        try {
            // Continue the filter chain
            chain.doFilter(request, response);
        } finally {
            // Clear the MDC after the request has been processed
            MDC.remove("requestUUID");
        }
    }
}
