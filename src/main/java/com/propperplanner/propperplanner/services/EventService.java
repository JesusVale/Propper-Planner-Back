package com.propperplanner.propperplanner.services;

import com.propperplanner.propperplanner.dao.EventDAO;
import com.propperplanner.propperplanner.entity.Event;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    public void removeEvent(Integer id) {
        eventDAO.deleteById(id);
    }
}
