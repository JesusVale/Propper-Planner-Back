package com.propperplanner.propperplanner.controllers;

import com.propperplanner.propperplanner.entity.RoutineActivity;
import com.propperplanner.propperplanner.exceptions.OverlapException;
import com.propperplanner.propperplanner.services.RoutineActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routine")
public class RoutineActivityController {

    @Autowired
    RoutineActivityService routineActivityService;

    @GetMapping("/{day}")
    public ResponseEntity<?> getEventsByDay(@PathVariable String day){
        List<RoutineActivity> activities = routineActivityService.getRoutineByDay(day);
        return ResponseEntity.ok(activities);
    }

    @PostMapping("/")
    public ResponseEntity<?> addRoutineActivity(@RequestBody RoutineActivity routineActivity){
        RoutineActivity newRoutineActivity = routineActivityService.addRoutineActivity(routineActivity);
        return  ResponseEntity.status(HttpStatus.CREATED).body(newRoutineActivity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoutineActivity(@PathVariable Integer id){
        routineActivityService.deleteRoutineActivity(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(value = OverlapException.class)
    public ResponseEntity<String> handleOverlapException(OverlapException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
