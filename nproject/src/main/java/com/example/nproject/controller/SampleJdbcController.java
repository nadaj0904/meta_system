package com.example.nproject.controller;

import com.example.nproject.dto.SampleDto;
import com.example.nproject.mapper.SampleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleJdbcController {

    private static final Logger logger = LoggerFactory.getLogger(SampleJdbcController.class);

    @Autowired
    private SampleMapper sampleMapper;

    @GetMapping("/sample/jdbc")
    public java.util.Map<String, Object> testConnection() {
        logger.info("=== Starting Supabase DB Connection Test ===");

        try {
            logger.debug("Calling sampleMapper.selectVersion()");
            SampleDto result = sampleMapper.selectVersion();

            logger.info("✓ Successfully connected to Supabase DB");
            logger.info("Query result: {}", result);
            logger.info("=== Supabase DB Connection Test Completed ===");

            // Create response map for browser display
            java.util.Map<String, Object> response = new java.util.HashMap<>();
            response.put("message", "Supabase DB Connection Success!!");
            response.put("status", "success");
            response.put("data", result);
            response.put("timestamp", java.time.LocalDateTime.now().toString());

            return response;
        } catch (Exception e) {
            logger.error("✗ Failed to connect to Supabase DB", e);
            logger.error("Error message: {}", e.getMessage());

            // Create error response
            java.util.Map<String, Object> errorResponse = new java.util.HashMap<>();
            errorResponse.put("message", "Supabase DB Connection Failed!");
            errorResponse.put("status", "error");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("timestamp", java.time.LocalDateTime.now().toString());

            return errorResponse;
        }
    }
}
