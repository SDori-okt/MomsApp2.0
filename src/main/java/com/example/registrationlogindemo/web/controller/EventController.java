package com.example.registrationlogindemo.web.controller;

import com.example.registrationlogindemo.persistence.entity.EventEntity;
import com.example.registrationlogindemo.persistence.entity.UserEntity;
import com.example.registrationlogindemo.service.EventService;
import com.example.registrationlogindemo.web.model.CreateEventRequest;
import com.example.registrationlogindemo.web.model.CreateUserRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@Validated
@RequestMapping
public class EventController {
    private EventService eventService;

    @RequestMapping("/events")
    public String getEvents(Model model) {
        List<EventEntity> events = eventService.findAll();
        model.addAttribute("events", events);
        return "events";
    }

    @GetMapping("/create_event")
    public String createEventForm(Model model) throws Exception {
        model.addAttribute("event", new EventEntity());
        return "create_event";
    }

    @PostMapping(path = "/create_event")
    @ResponseStatus(HttpStatus.CREATED)
    public String addEvent(@ModelAttribute("event") CreateEventRequest createEventRequest, Model model) throws Exception {
        model.addAttribute("event", createEventRequest);
        try {
            eventService.addEvent(createEventRequest);
        } catch (Exception e){
            return "create_event_error";
        }
        return "event_created";
    }
    //Endpoints for Postman
    //------------------------------------------------------------------------------------------------------------------

    // Endpoint for Postman test
    @PostMapping("/api/addEvent")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEvent(@Valid @RequestBody CreateEventRequest createEventRequest) {
        eventService.addEvent(createEventRequest);
    }

}
