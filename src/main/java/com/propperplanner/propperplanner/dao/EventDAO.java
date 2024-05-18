package com.propperplanner.propperplanner.dao;

import com.propperplanner.propperplanner.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EventDAO extends JpaRepository<Event, Integer> {

    List<Event> findByDate(Date day);

}
