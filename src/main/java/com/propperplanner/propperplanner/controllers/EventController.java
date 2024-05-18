package com.propperplanner.propperplanner.controllers;

import com.propperplanner.propperplanner.entity.Event;
import com.propperplanner.propperplanner.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/{day}")
    public ResponseEntity<?> getEventByDay(@PathVariable String day){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try{
            Date eventDate = formatter.parse(day);
            List<Event> events = eventService.getEvents(eventDate);
            return ResponseEntity.ok(events);
        } catch (Exception error){
            return ResponseEntity.badRequest().body("La fecha no tiene el formato correcto");
        }


    }

    @PostMapping("/")
    public ResponseEntity<Event> addEvent(@RequestBody Event event){
        Event newEvent = eventService.addEvent(event);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEvent(@PathVariable Integer id){
        eventService.removeEvent(id);
        return ResponseEntity.noContent().build();
    }

}
