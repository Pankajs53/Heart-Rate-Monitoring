package com.example.Heart_Rate_Monitoring.service;

import com.example.Heart_Rate_Monitoring.dto.PatientRequest;
import com.example.Heart_Rate_Monitoring.entity.Patient;
import com.example.Heart_Rate_Monitoring.entity.User;
import com.example.Heart_Rate_Monitoring.repository.PatientRepository;
import com.example.Heart_Rate_Monitoring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private  PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    public Patient getPatientById(Long patientId) {
        return patientRepository.findById(patientId).orElse(null);
    }



    public Boolean addPatient(PatientRequest patientRequest) {
        User user = userRepository.findById(patientRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("User found " + user);

        // Create a new Patient entity
        Patient patient = new Patient();
        patient.setName(patientRequest.getName());
        patient.setAge(patientRequest.getAge());
        patient.setGender(patientRequest.getGender());
        patient.setPhoneNo(patientRequest.getPhoneNo());
        patient.setUser(user);

        // Save the patient
        patientRepository.save(patient);

        return  true;
    }
}
