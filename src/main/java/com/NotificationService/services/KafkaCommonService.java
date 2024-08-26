package com.NotificationService.services;

import com.NotificationService.DTO.KafkaApoiDTO;
import com.NotificationService.DTO.KafkaDocDTO;
import com.NotificationService.DTO.KafkaPatDTO;
import com.NotificationService.config.AppConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

@Service
public class KafkaCommonService {

    private final ObjectMapper objectMapper;

    public KafkaCommonService() {
        this.objectMapper = new ObjectMapper();
        // Register the JavaTimeModule to handle Java 8 date/time types
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @KafkaListener(topics = AppConstants.LOCATION_UPDATE_TOPIC, groupId = "group-1")
    public void consume(String message) {
        try {
            // Deserialize the JSON string into a KafkaApoiDTO object
            KafkaApoiDTO appointmentDTO = objectMapper.readValue(message, KafkaApoiDTO.class);

            // Access individual properties
            String appointmentId = appointmentDTO.getAppointmentId();
            LocalDate date = appointmentDTO.getDate();
            LocalTime time = appointmentDTO.getTime();
            String description = appointmentDTO.getDescription();

            // Access nested objects
            KafkaDocDTO doctor = appointmentDTO.getKafkaDocDTO();
            KafkaPatDTO patient = appointmentDTO.getKafkaPatDTO();

            // Example: Use the properties
            System.out.println("Appointment ID: " + appointmentId);
            System.out.println("Date: " + date);
            System.out.println("Time: " + time);
            System.out.println("Description: " + description);
            System.out.println("Doctor: " + doctor.getName() + ", Specialization: " + doctor.getSpecialization());
            System.out.println("Patient: " + patient.getName() + ", Age: " + patient.getAge());

        } catch (Exception e) {
            System.err.println("Failed to deserialize JSON to KafkaApoiDTO: " + e.getMessage());
        }
    }
}
