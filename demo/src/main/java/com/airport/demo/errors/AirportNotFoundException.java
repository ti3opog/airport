package com.airport.demo.errors;

public class AirportNotFoundException extends RuntimeException{
    public AirportNotFoundException(Long id){super("Could not find airport: "+id);}
}
