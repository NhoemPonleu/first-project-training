package com.acledabankplc.model;

import com.acledabankplc.utils.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;
@Data
@Table(name = "tbl_students")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Student extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name",length = 100,nullable = false)
    private String firstName;
    @Column(name = "last_name",length = 100,nullable = false)
    private String lastName;
    @Column(name = "phone_number",length = 11)
    private String phoneNumber;
    @Column(name = "date_of_birth",length = 10)
    private Date dob;
    private String email;
    @Column(name = "date_of_register")
    private LocalDate registerDate;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


}
