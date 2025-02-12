package com.example.Heart_Rate_Monitoring.repository;

import com.example.Heart_Rate_Monitoring.entity.HeartRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;


public interface HeartRateRepository extends JpaRepository<HeartRate,Long> {
    List<HeartRate> findByPatientId(Long patientId);
}
