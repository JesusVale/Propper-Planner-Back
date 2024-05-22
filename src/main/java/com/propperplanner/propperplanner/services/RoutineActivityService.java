package com.propperplanner.propperplanner.services;

import com.propperplanner.propperplanner.dao.RoutineActivityDAO;
import com.propperplanner.propperplanner.entity.RoutineActivity;
import com.propperplanner.propperplanner.enums.Day;
import com.propperplanner.propperplanner.exceptions.EntityNotFoundException;
import com.propperplanner.propperplanner.exceptions.OverlapException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class RoutineActivityService implements IRoutineActivityService{

    @Autowired
    RoutineActivityDAO routineActivityDAO;

    @Override
    public List<RoutineActivity> getRoutineByDay(String day) {
        List<RoutineActivity> routineActivities = routineActivityDAO.getRoutineByDay(day);
        Collections.sort(routineActivities, new Comparator<RoutineActivity>() {
            @Override
            public int compare(RoutineActivity r1, RoutineActivity r2) {
                return r1.getStart().compareTo(r2.getStart());
            }
        });
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
    public RoutineActivity updateRoutineActivity(Integer id, RoutineActivity routineActivity) {

        List<RoutineActivity> routineActivities = routineActivityDAO.findAll();

        for(RoutineActivity activity: routineActivities){
            if(overlaps(routineActivity, activity)){
                throw new OverlapException("Times Overlap with activity: " + activity.getName());
            }
        }

        Optional<RoutineActivity> routineActivityOptional = routineActivityDAO.findById(id);
        if(routineActivityOptional.isPresent()){
            RoutineActivity routineActivityFound = routineActivityOptional.get();
            routineActivityFound.setDays(routineActivity.getDays());
            routineActivityFound.setEnd(routineActivity.getEnd());
            routineActivityFound.setStart(routineActivity.getStart());
            routineActivityFound.setName(routineActivity.getName());
            return routineActivityDAO.save(routineActivityFound);
        } else{
            throw new EntityNotFoundException("No esiste una actividad con el id: " + id);
        }

    }

    @Override
    public void deleteRoutineActivity(Integer id) {
        routineActivityDAO.deleteById(id);
    }

    public boolean overlaps(RoutineActivity activity, RoutineActivity otherActivity){
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
