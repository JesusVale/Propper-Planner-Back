package com.propperplanner.propperplanner.services;

import com.propperplanner.propperplanner.entity.RoutineActivity;
import com.propperplanner.propperplanner.enums.Day;

import java.util.List;

public interface IRoutineActivityService {

    public List<RoutineActivity> getRoutineByDay(String day);

    public RoutineActivity addRoutineActivity(RoutineActivity routineActivity);

    public void deleteRoutineActivity(Integer id);

}