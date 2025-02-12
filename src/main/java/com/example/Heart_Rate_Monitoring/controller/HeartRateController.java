package com.example.Heart_Rate_Monitoring.controller;

import com.example.Heart_Rate_Monitoring.dto.HeartRateRequest;
import com.example.Heart_Rate_Monitoring.dto.HeartRateResponse;
import com.example.Heart_Rate_Monitoring.entity.HeartRate;
import com.example.Heart_Rate_Monitoring.service.HeartRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/heart-rate")
public class HeartRateController {

    @Autowired
    private HeartRateService heartRateService;

    // Add heart rate for a patient
    @PostMapping("/add")
    public ResponseEntity<?> addHeartRate(@RequestBody HeartRateRequest heartRateRequest) {
        heartRateService.addHeartRate(heartRateRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Heart rate recorded successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Retrieve heart rate data for a patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<HeartRateResponse>> getHeartRatesByPatient(@PathVariable Long patientId) {
        List<HeartRateResponse> heartRates = heartRateService.getHeartRatesByPatient(patientId);
        return ResponseEntity.ok(heartRates);
    }


}
