package com.airport.demo.controllers;

import com.airport.demo.entity.Airport;
import com.airport.demo.errors.AirportNotFoundException;
import com.airport.demo.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airport")
public class AirportController {
    @Autowired
    private AirportRepository repository;

    public AirportController(AirportRepository repository){ this.repository = repository;}

    @GetMapping
    public List<Airport> findAll(){return repository.findAll();}

    @GetMapping("/{id}")
    Airport findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AirportNotFoundException(id));
    }

    @PutMapping("/{id}")
    Airport replaceAirport(@RequestBody Airport newAirport, @PathVariable Long id){
        return repository.findById(id)
                .map(airport -> {
                    airport.setName(newAirport.getName());
                    airport.setAge(newAirport.getAge());
                    airport.setGender(newAirport.getGender());
                    airport.setFlight(newAirport.getFlight());
                    airport.setNumber(newAirport.getNumber());
                    airport.setSerial(newAirport.getSerial());
                    airport.setSurname(newAirport.getSurname());
                    airport.setId(id);
                    return repository.save(airport);
                })
                .orElseGet(() -> repository.save(newAirport));
    }

    @DeleteMapping("/{id}")
    void deleteAirport(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PostMapping
    public Airport createAirport(@RequestBody Airport airport){return repository.save(airport);}
}
