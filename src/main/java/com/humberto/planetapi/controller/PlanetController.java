package com.humberto.planetapi.controller;

import com.humberto.planetapi.domain.Planet;
import com.humberto.planetapi.domain.PlanetCreateDTO;
import com.humberto.planetapi.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/planets")
public class PlanetController {

    @Autowired
    PlanetService service;

    @PostMapping
    ResponseEntity<Planet> create(@RequestBody PlanetCreateDTO request){
        Planet planet = service.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(planet);
    }
}
