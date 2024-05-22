package com.propperplanner.propperplanner.services;

import com.propperplanner.propperplanner.entity.RoutineActivity;
import com.propperplanner.propperplanner.enums.Day;

import java.util.List;

public interface IRoutineActivityService {

    public RoutineActivity getRoutineActivityById(Integer id);
    public List<RoutineActivity> getRoutineByDay(String day);

    public RoutineActivity addRoutineActivity(RoutineActivity routineActivity);

    public RoutineActivity updateRoutineActivity(Integer id, RoutineActivity routineActivity);

    public void deleteRoutineActivity(Integer id);

}
