package com.example.Heart_Rate_Monitoring.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientRequest {
    private String name;
    private Integer age;
    private String gender;
    private String phoneNo;
    private Long userId;
}
