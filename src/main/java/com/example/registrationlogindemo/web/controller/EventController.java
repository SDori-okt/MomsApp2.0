package com.example.registrationlogindemo.web.controller;

import com.example.registrationlogindemo.persistence.entity.EventEntity;
import com.example.registrationlogindemo.service.EventService;
import com.example.registrationlogindemo.web.model.CreateEventRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@Validated
@RequestMapping
public class EventController {
    private EventService eventService;

    @GetMapping("/get_event")
    public String createEventView(Model model) {
        model.addAttribute("event", new EventEntity());
        return "create_event";
    }

    @PostMapping(path = "/create_event")
    @ResponseStatus(HttpStatus.CREATED)
    public String addEvent(@ModelAttribute("event") CreateEventRequest createEventRequest, Model model) throws Exception {
        model.addAttribute("event", createEventRequest);
        eventService.addEvent(createEventRequest);
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
