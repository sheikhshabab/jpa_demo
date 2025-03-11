package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Subject cannot be null")
    @Size(min = 2, max = 50, message = "Street must be between 2 and 50 characters")
    private String subject;


    @NotNull(message = "Grade cannot be null")
    @Size(min = 2, max = 50, message = "Street must be between 2 and 50 characters")
    private String grade;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject(){
        return this.subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getGrade(){
        return this.grade;
    }

    public void setGrade(String grade){
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
