package com.example.Heart_Rate_Monitoring.controller;

import com.example.Heart_Rate_Monitoring.dto.PatientRequest;
import com.example.Heart_Rate_Monitoring.entity.Patient;
import com.example.Heart_Rate_Monitoring.entity.User;
import com.example.Heart_Rate_Monitoring.service.PatientService;
import com.example.Heart_Rate_Monitoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @PostMapping("/add")
    public ResponseEntity<?> addPatient(@RequestBody PatientRequest patientRequest) {
        try{
            System.out.println("Here patient is " + patientRequest);
            patientService.addPatient(patientRequest);
            return new ResponseEntity<>("Added user",HttpStatus.OK);
        }catch (Exception E){
            return new ResponseEntity<>("Error in adding user" + E,HttpStatus.NOT_FOUND);
        }

    }

    // Retrieve all patients for a specific user
    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long patientId) {
        System.out.println("Patient id is " + patientId);
        Patient patient = patientService.getPatientById(patientId);
        if (patient == null) {
            return ResponseEntity.notFound().build();
        }

        // Set the password to null before returning the response
        if (patient.getUser() != null) {
            patient.getUser().setPassword(null);
        }

        return ResponseEntity.ok(patient);
    }


}
