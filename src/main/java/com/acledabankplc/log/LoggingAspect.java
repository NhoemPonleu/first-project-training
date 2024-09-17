package com.acledabankplc.log;

import com.acledabankplc.config.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    private final ObjectMapper objectMapper;
    private final ConcurrentHashMap<String, AtomicInteger> userRequestCounts = new ConcurrentHashMap<>();

    @Autowired
    private UserLoggingService userLoggingService; // Service to log user actions in DB

    public LoggingAspect() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Register the module here
    }

    @Around("execution(* com.acledabankplc.controller..*(..))")
    public Object logExecutionDetails(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication != null ? authentication.getName() : "anonymous";
        Object principal = authentication.getPrincipal();
        String userId = null;
        if (principal instanceof User) {
            userId = String.valueOf(((User) principal).getId());
        }
        userRequestCounts.putIfAbsent(username, new AtomicInteger(1));
        AtomicInteger requestCount = userRequestCounts.get(username);

        logger.info("User: {}, Total requests processed: {}", username, requestCount.get());

        Object[] requestData = joinPoint.getArgs();
        try {
            String requestJson = objectMapper.writeValueAsString(requestData);
            logger.info("Method {} called with requestData: {}", joinPoint.getSignature(), requestJson);
        } catch (Exception e) {
            logger.warn("Failed to log requestData as JSON", e);
        }

        long start = System.currentTimeMillis();
        String actionSuccessYN = "Y";  // Default to success
        Object responseData = null;    // Initialize responseData to null

        try {
            responseData = joinPoint.proceed();  // Proceed with method execution
        } catch (Throwable ex) {
            actionSuccessYN = "N";  // Log as failed if exception occurs
            throw ex;  // Rethrow the exception
        } finally {
            long executionTime = System.currentTimeMillis() - start;

            try {
                String responseJson = objectMapper.writeValueAsString(responseData);
                logger.info("Method {} returned responseData: {} in {} ms", joinPoint.getSignature(), responseJson, executionTime);
            } catch (Exception e) {
                logger.warn("Failed to log responseData as JSON", e);
            }

            // Log the method name as the action and whether it succeeded or not
            String action = joinPoint.getSignature().getName();
            userLoggingService.logUserAction(username, action, actionSuccessYN, Integer.valueOf(userId));
            requestCount.incrementAndGet();
        }

        return responseData;
    }

}
