package com.acledabankplc.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class UserLoggingService {

    private final TracUserLoggingRepository loggingRepository;

    @Autowired
    public UserLoggingService(TracUserLoggingRepository loggingRepository) {
        this.loggingRepository = loggingRepository;
    }

    public void logUserAction(String username, String action,String actionSuccessYN,Integer userId) {
        TracUserLogging logEntry = new TracUserLogging();
        logEntry.setUserNameLogging(username);
        logEntry.setActionDate(LocalDateTime.now());
        logEntry.setLoggingAction(action); // Store the method name as the action
        logEntry.setTimeAction(LocalTime.now());
        logEntry.setActionSuccessYN(actionSuccessYN);
        logEntry.setUserId(userId);
        loggingRepository.save(logEntry); // Save the log entry in the database
    }
}
