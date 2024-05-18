package com.propperplanner.propperplanner.entity;


import com.propperplanner.propperplanner.enums.Day;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.sql.Time;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table( name =  "routine_activities")
public class RoutineActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "start")
    private Time start;

    @Column(name = "end")
    private Time end;

    @ElementCollection(targetClass = Day.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "day_activity", joinColumns = @JoinColumn(name = "routine_activity"))
    @Column(name = "day_activity")
    private List<Day> days;

}
