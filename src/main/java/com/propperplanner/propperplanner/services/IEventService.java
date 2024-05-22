package com.propperplanner.propperplanner.services;

import com.propperplanner.propperplanner.entity.Event;

import java.util.Date;
import java.util.List;

public interface IEventService {

    public List<Event> getEvents(Date day);

    public Event addEvent(Event event);

    public Event updateEvent(Integer id, Event event);

    public void removeEvent(Integer id);

    public Event getEventById(Integer id);

}
