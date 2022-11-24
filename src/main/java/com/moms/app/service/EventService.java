package com.moms.app.service;

import com.moms.app.persistence.entity.EventEntity;
import com.moms.app.persistence.repository.EventRepository;
import com.moms.app.service.mapper.EventMapper;
import com.moms.app.web.model.CreateEventRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    private EventRepository eventRepository;

    private EventMapper eventMapper;

    public List<EventEntity> findAll(){
        return eventRepository.findAll();
    }

    public EventEntity addEvent(CreateEventRequest createEventRequest) {
        EventEntity event = eventMapper.map(createEventRequest);
        return eventRepository.save(event);
    }

}
