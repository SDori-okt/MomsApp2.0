package com.moms.app.web.controller;

import com.moms.app.persistence.entity.EventEntity;
import com.moms.app.service.EventService;
import com.moms.app.web.model.CreateEventRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@Validated
@RequestMapping
public class EventController extends AbstractController {
    private EventService eventService;

    @GetMapping("/get_event")
    public String createEventView(Model model, HttpSession session) {
        if (logInCheck()) {
            return "login";
        }
        model.addAttribute("event", new EventEntity());
        return "create_event";
    }

    @GetMapping("/")
    public String listAllEvents(Model model, HttpSession session) {
        if (logInCheck()) {
            return "login";
        }
        List<EventEntity> all = eventService.findAllEvents();
        model.addAttribute("events", all);
        return "index";
    }

    @GetMapping("/create_event")
    public String createEvent(Model model, HttpSession session) {
        if (logInCheck()) {
            return "login";
        }
        model.addAttribute("event", new EventEntity());
        return "create_event";
    }

    @PostMapping(path = "/create_event")
    @ResponseStatus(HttpStatus.CREATED)
    public String addEvent(@ModelAttribute("event") CreateEventRequest createEventRequest, Model model) {
        model.addAttribute("event", createEventRequest);
        if (createEventRequest.getEventName().isBlank()
                || createEventRequest.getEventDateTime() == null
                || createEventRequest.getDescription().isBlank()) {
            return "create_event_error";
        } else {
            eventService.addEvent(createEventRequest);
            return "event_created";
        }
    }

    @GetMapping("/own_events")
    public String listAllOwnEvents(Model model, HttpSession session) {
        if (logInCheck()) {
            return "login";
        }
        List<EventEntity> all = eventService.findAllLoggedPlayerEvents();
        model.addAttribute("events", all);
        return "index";
    }
}