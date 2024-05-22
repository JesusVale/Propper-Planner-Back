package com.propperplanner.propperplanner.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "date")
    private Date date;

    @Column(name = "name")
    private String name;

    public Event(Date date, String name) {
        this.date = date;
        this.name = name;
    }

}
