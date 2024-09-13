package com.acledabankplc.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Create an ObjectMapper instance
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Around("execution(* com.acledabankplc.controller..*(..))") // Pointcut for all controller methods
    public Object logExecutionDetails(ProceedingJoinPoint joinPoint) throws Throwable {
        // Log requestData (method parameters) as JSON
        Object[] requestData = joinPoint.getArgs();
        try {
            String requestJson = objectMapper.writeValueAsString(requestData);
            logger.info("Method {} called with requestData: {}", joinPoint.getSignature(), requestJson);
        } catch (Exception e) {
            logger.warn("Failed to log requestData as JSON", e);
        }

        long start = System.currentTimeMillis();

        // Proceed with the method execution
        Object responseData = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        // Log responseData as JSON
        try {
            String responseJson = objectMapper.writeValueAsString(responseData);
            logger.info("Method {} returned responseData: {} in {} ms", joinPoint.getSignature(), responseJson, executionTime);
        } catch (Exception e) {
            logger.warn("Failed to log responseData as JSON", e);
        }

        return responseData;
    }
}
