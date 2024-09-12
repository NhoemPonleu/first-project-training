package com.acledabankplc.dto;

import com.acledabankplc.validation.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
@Data
public class StudentDTO {
    @NotBlank(message = "Name is mandatory")
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date dob;
    @ValidEmail
    private String email;
    private Long courseId;
}
