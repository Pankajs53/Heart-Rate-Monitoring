package com.example.Heart_Rate_Monitoring.entity;
import com.example.Heart_Rate_Monitoring.entity.*;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "heart_rate")
@Data
public class HeartRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    private int bpm;

    private LocalDateTime timeStamp = LocalDateTime.now();
}
