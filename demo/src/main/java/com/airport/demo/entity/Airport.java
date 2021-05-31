package com.airport.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.This;

import javax.persistence.*;


@Entity
@Table(name = "AIRPORT")
@NoArgsConstructor
@Data
public class Airport {

    private @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long age;
    Long flight;
    String gender;
    String name;
    Long number;
    Long serial;
    String surname;

    public Airport(Long id, Long age, Long flight, String gender, String name, Long number, Long serial, String surname) {

        this.id = id;
        this.age = age;
        this.flight = flight;
        this.gender =gender;
        this.name = name;
        this.number = number;
        this.serial = serial;
        this.surname = surname;
    }

    public Airport(long age, long flight, String gender, String name, long number, long serial, String surname) {
        this.age = age;
        this.flight = flight;
        this.gender =gender;
        this.name = name;
        this.number = number;
        this.serial = serial;
        this.surname = surname;
    }
}
