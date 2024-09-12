package com.acledabankplc.model;


import com.acledabankplc.utils.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "course")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Course extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;
    private String courseCode;
    private String description;


}
