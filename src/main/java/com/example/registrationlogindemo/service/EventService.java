package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.persistence.entity.EventEntity;
import com.example.registrationlogindemo.persistence.repository.EventRepository;
import com.example.registrationlogindemo.service.mapper.EventMapper;
import com.example.registrationlogindemo.web.model.CreateEventRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventService {

    private EventRepository eventRepository;

    private EventMapper eventMapper;

    public EventEntity addEvent(CreateEventRequest createEventRequest) {
        EventEntity event = eventMapper.map(createEventRequest);
        return eventRepository.save(event);
    }

}
