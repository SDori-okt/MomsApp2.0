package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.persistence.entity.EventEntity;
import com.example.registrationlogindemo.persistence.entity.UserEntity;
import com.example.registrationlogindemo.persistence.repository.EventRepository;
import com.example.registrationlogindemo.persistence.repository.UserRepository;
import com.example.registrationlogindemo.service.mapper.EventMapper;
import com.example.registrationlogindemo.web.model.CreateEventRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    private UserRepository userRepository;

    private EventRepository eventRepository;

    private EventMapper eventMapper;

    public EventEntity addEvent(CreateEventRequest createEventRequest) {
        EventEntity event = eventMapper.map(createEventRequest);

        String currentPrincipalName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUsername(currentPrincipalName).get();

        event.setCreatedBy(userEntity);
        eventRepository.save(event);

        userEntity.getEvents_taken().add(event);
        userRepository.save(userEntity);
        return event;
    }

    public List<EventEntity> findAll() {
        return eventRepository.findAll();
    }

}
