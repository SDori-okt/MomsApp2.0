package com.example.registrationlogindemo.service.mapper;

import com.example.registrationlogindemo.persistence.entity.EventEntity;
import com.example.registrationlogindemo.web.model.CreateEventRequest;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public EventEntity map(CreateEventRequest createEventRequest) {
        return EventEntity.builder()
                .eventName(createEventRequest.getEventName())
                .description(createEventRequest.getDescription())
                .eventDateTime(createEventRequest.getEventDateTime())
                .build();
    }
}
