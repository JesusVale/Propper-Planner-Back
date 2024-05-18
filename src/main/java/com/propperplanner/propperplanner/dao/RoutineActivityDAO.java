package com.propperplanner.propperplanner.dao;

import com.propperplanner.propperplanner.entity.RoutineActivity;
import com.propperplanner.propperplanner.enums.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoutineActivityDAO extends JpaRepository<RoutineActivity, Integer> {

    @Query(value = "SELECT * FROM routine_activities " +
            "LEFT JOIN day_activity ON routine_activities.id = day_activity.routine_activity " +
            "WHERE day_activity.day_activity = :day", nativeQuery = true)
    List<RoutineActivity> getRoutineByDay(String day);

}
