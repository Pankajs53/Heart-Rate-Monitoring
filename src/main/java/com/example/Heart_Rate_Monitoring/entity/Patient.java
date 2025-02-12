package com.example.Heart_Rate_Monitoring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    private String name;
    private Integer age;
    private String gender;

    @Column(unique = true, nullable = true)
    private String phoneNo; // Nullable for children or elderly patients
}
