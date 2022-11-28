package com.moms.app.service;

import com.moms.app.persistence.entity.EventEntity;
import com.moms.app.persistence.repository.EventRepository;
import com.moms.app.service.mapper.EventMapper;
import com.moms.app.web.model.CreateEventRequest;
import com.moms.app.persistence.entity.UserEntity;
import com.moms.app.persistence.repository.UserRepository;

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
