package com.example.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "patient", groupId = "analytics-service-test")
    public void consumeEvent(byte[] eventData) {
        try {
            PatientEvent event = PatientEvent.parseFrom(eventData);
            // Process the event (e.g., log it, update analytics, etc.)
            System.out.println("Received event: " + event.getEventType() + " for patient ID: " + event.getPatientId()
                    + " with name: " + event.getName() + " and email: " + event.getEmail());
        } catch (InvalidProtocolBufferException e) {
            log.error("Error parsing patient event from Kafka: {}", e.getMessage());
        }
    }
}
