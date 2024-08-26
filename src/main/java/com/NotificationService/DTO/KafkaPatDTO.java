package com.NotificationService.DTO;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KafkaPatDTO {
    private String patientId;
    private String name;
    private int age;
    private String mobileNo;
}
