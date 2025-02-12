package com.example.Heart_Rate_Monitoring.dto;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HeartRateRequest {
    private Long id;
    private Long patientId;  // Only include patient ID instead of full Patient object
    private int bpm;
    private LocalDateTime timeStamp;
}
