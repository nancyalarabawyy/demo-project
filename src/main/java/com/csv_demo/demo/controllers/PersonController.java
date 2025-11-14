package com.csv_demo.demo.controllers;
import com.csv_demo.demo.model.Person;
import com.csv_demo.demo.services.CSVService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/persons")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {
    public final CSVService csvService;
    public PersonController(CSVService csvService) {
        this.csvService = csvService;
    }
    @GetMapping
    public List<Person> readCSV() {
        return this.csvService.readCSV();
    }
    @PostMapping
    public void addPerson(@RequestBody Person person) {
        this.csvService.writeToCSV(person);
    }
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable int id) {
        this.csvService.deleteCSV(id);
    }
}
