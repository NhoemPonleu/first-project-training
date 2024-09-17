package com.acledabankplc.log;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "trac_user_logging")
@Data
public class TracUserLogging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userNameLogging;
    private LocalDateTime actionDate;
    private String loggingAction;
    private LocalTime timeAction;
    private String actionSuccessYN;
    private Integer userId;
}
