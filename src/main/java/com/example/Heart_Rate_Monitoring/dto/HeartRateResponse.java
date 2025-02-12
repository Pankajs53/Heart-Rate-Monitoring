package com.example.Heart_Rate_Monitoring.dto;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HeartRateResponse {
    private int bpm;
    private LocalDateTime timeStamp;
}
