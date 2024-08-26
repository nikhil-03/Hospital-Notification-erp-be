package com.NotificationService.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KafkaDocDTO {
    private String doctorId;
    private String name;
    private String specialization;
}
