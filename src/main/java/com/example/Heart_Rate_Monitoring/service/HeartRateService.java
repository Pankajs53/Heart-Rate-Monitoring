package com.example.Heart_Rate_Monitoring.service;


import com.example.Heart_Rate_Monitoring.dto.HeartRateRequest;
import com.example.Heart_Rate_Monitoring.dto.HeartRateResponse;
import com.example.Heart_Rate_Monitoring.entity.HeartRate;
import com.example.Heart_Rate_Monitoring.entity.Patient;
import com.example.Heart_Rate_Monitoring.exception.ResourceNotFoundException;
import com.example.Heart_Rate_Monitoring.repository.HeartRateRepository;
import com.example.Heart_Rate_Monitoring.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class HeartRateService {
    @Autowired
    private HeartRateRepository heartRateRepository;

    @Autowired
    private PatientRepository patientRepository;


    public List<HeartRateResponse> getHeartRatesByPatient(Long patientId) {
        List<HeartRate> heartRateList = heartRateRepository.findByPatientId(patientId);
        List<HeartRateResponse> responseList = new ArrayList<>();

        // Change to DTO
        for (HeartRate hr : heartRateList) {
            responseList.add(new HeartRateResponse(hr.getBpm(), hr.getTimeStamp()));
        }

        // Sort the data before sending back response
        Collections.sort(responseList, new Comparator<HeartRateResponse>() {
            @Override
            public int compare(HeartRateResponse a, HeartRateResponse b) {
                return b.getTimeStamp().compareTo(a.getTimeStamp()); // Descending order
            }
        });

        return responseList;

    }

    public HeartRate addHeartRate(HeartRateRequest heartRateRequest) {
        Long patientId = heartRateRequest.getPatientId();
        Patient patient = patientRepository.findById(patientId).orElse(null);

        if (patient == null) {
            throw new ResourceNotFoundException("Patient not found: " + patientId);
        }

        HeartRate heartRate = new HeartRate();
        heartRate.setPatient(patient);
        heartRate.setBpm(heartRateRequest.getBpm());
        heartRate.setTimeStamp(heartRateRequest.getTimeStamp());
        heartRate.setTimeStamp(heartRateRequest.getTimeStamp() != null ? heartRateRequest.getTimeStamp() : LocalDateTime.now());

        return heartRateRepository.save(heartRate);
    }
}
