package com.humberto.planetapi.service;

import com.humberto.planetapi.domain.Planet;
import com.humberto.planetapi.domain.PlanetCreateDTO;
import com.humberto.planetapi.repository.PlanetRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanetService {

    private final PlanetRepository repository;

    PlanetService(PlanetRepository planetRepository) {
        this.repository = planetRepository;
    }

    public Planet create(PlanetCreateDTO request) {
        return repository.save(new Planet(request));
    }
}

