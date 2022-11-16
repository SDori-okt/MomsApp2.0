package com.example.registrationlogindemo.web.controller;

import com.example.registrationlogindemo.service.EventService;
import com.example.registrationlogindemo.web.model.CreateEventRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@AllArgsConstructor
@Validated
@RequestMapping
public class EventController {
    private EventService eventService;

    //Endpoints for Postman
    //------------------------------------------------------------------------------------------------------------------

    // Endpoint for Postman test
    @PostMapping("/api/addEvent")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEvent(@Valid @RequestBody CreateEventRequest createEventRequest) {
        eventService.addEvent(createEventRequest);
    }

}
