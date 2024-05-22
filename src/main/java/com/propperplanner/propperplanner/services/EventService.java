package com.propperplanner.propperplanner.services;

import com.propperplanner.propperplanner.dao.EventDAO;
import com.propperplanner.propperplanner.entity.Event;
import com.propperplanner.propperplanner.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService implements IEventService {

    @Autowired
    EventDAO eventDAO;

    @Override
    public List<Event> getEvents(Date day) {
        List<Event> events = eventDAO.findByDate(day);
        return events;
    }

    @Transactional
    @Override
    public Event addEvent(Event event) {
        event.getDate().setTime(event.getDate().getTime() + (1000 * 60 * 60 * 24));
        Event newEvent = eventDAO.save(event);
        return newEvent;
    }

    @Transactional
    @Override
    public Event updateEvent(Integer id, Event event) {
        Optional<Event> eventEdit = eventDAO.findById(id);
        if(eventEdit.isPresent()){
            Event eventFound = eventEdit.get();
            eventFound.setDate(event.getDate());
            eventFound.setName(event.getName());
            return eventDAO.save(eventFound);
        } else {
            throw new EntityNotFoundException("No existe un Evento con el id: "+id);
        }
    }

    @Transactional
    @Override
    public void removeEvent(Integer id) {
        eventDAO.deleteById(id);
    }
}
