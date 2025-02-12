package com.example.Heart_Rate_Monitoring.repository;

import com.example.Heart_Rate_Monitoring.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    List<Patient> findByUserId(Long userId);
}
