package com.humberto.planetapi.controller;

import com.humberto.planetapi.domain.Planet;
import com.humberto.planetapi.domain.PlanetDTO;
import com.humberto.planetapi.service.PlanetService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planets")
public class PlanetController {

    @Autowired
    PlanetService service;

    @PostMapping
    ResponseEntity<Planet> create(@RequestBody PlanetDTO request){
        Planet planet = service.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(planet);
    }

    @GetMapping("{id}")
    ResponseEntity<PlanetDTO> getPlanetById(@PathVariable Long id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("{name}")
    ResponseEntity<PlanetDTO> getPlanetByName(@PathVariable String name){
        return service.findByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    ResponseEntity<List<PlanetDTO>> list(@RequestParam(required = false) String terrain,
                                         @RequestParam(required = false) String climate){

        List<PlanetDTO> planets = service.list(terrain,climate);
        return ResponseEntity.ok(planets);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id){
        service.remove(id);
        return ResponseEntity.noContent().build();
    }

}
