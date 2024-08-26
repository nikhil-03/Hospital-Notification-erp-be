package com.NotificationService.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KafkaApoiDTO {
    private String appointmentId;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;

    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime time;

    @Override
    public String toString() {
        return "KafkaApoiDTO{" +
                "appointmentId='" + appointmentId + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", description='" + description + '\'' +
                ", kafkaDocDTO=" + kafkaDocDTO +
                ", kafkaPatDTO=" + kafkaPatDTO +
                '}';
    }

    private String description;

    @JsonProperty("doctor")
    private KafkaDocDTO kafkaDocDTO;

    @JsonProperty("patient")
    private KafkaPatDTO kafkaPatDTO;
}
