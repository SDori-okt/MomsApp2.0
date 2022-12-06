package com.moms.app.service.mapper;

import com.moms.app.persistence.entity.EventEntity;
import com.moms.app.web.model.CreateEventRequest;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public EventEntity map(CreateEventRequest createEventRequest) {
        return EventEntity.builder()
                .eventName(createEventRequest.getEventName())
                .description(createEventRequest.getDescription())
                .eventDateTime(createEventRequest.getEventDateTime())
                .createdBy(createEventRequest.getUserEntity())
                .build();
    }
}