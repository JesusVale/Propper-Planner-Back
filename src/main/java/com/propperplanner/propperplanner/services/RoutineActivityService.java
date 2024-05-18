package com.propperplanner.propperplanner.services;

import com.propperplanner.propperplanner.dao.RoutineActivityDAO;
import com.propperplanner.propperplanner.entity.RoutineActivity;
import com.propperplanner.propperplanner.enums.Day;
import com.propperplanner.propperplanner.exceptions.OverlapException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutineActivityService implements IRoutineActivityService{

    @Autowired
    RoutineActivityDAO routineActivityDAO;

    @Override
    public List<RoutineActivity> getRoutineByDay(String day) {
        List<RoutineActivity> routineActivities = routineActivityDAO.getRoutineByDay(day);
        return routineActivities;
    }

    @Override
    public RoutineActivity addRoutineActivity(RoutineActivity routineActivity) {

        List<RoutineActivity> routineActivities = routineActivityDAO.findAll();

        for(RoutineActivity activity: routineActivities){
            if(overlaps(routineActivity, activity)){
                throw new OverlapException("Times Overlap with activity: " + activity.getName());
            }
        }

        RoutineActivity newRoutineActivity = routineActivityDAO.save(routineActivity);
        return newRoutineActivity;
    }

    @Override
    public void deleteRoutineActivity(Integer id) {
        routineActivityDAO.deleteById(id);
    }

    public boolean overlaps(RoutineActivity activity, RoutineActivity otherActivity){
        //Si contiene al menos 1 día
        boolean existsOne = false;
        for(Day day: activity.getDays()){
            if(otherActivity.getDays().contains(day)){
                existsOne = true;
                break;
            }
        }

        if(!existsOne){
            return false;
        }

        return activity.getStart().before(otherActivity.getEnd()) && otherActivity.getStart().before(activity.getEnd());
    }

}
