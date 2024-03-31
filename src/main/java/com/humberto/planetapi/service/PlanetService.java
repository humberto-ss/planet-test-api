package com.humberto.planetapi.service;

import com.humberto.planetapi.domain.Planet;
import com.humberto.planetapi.domain.PlanetDTO;
import com.humberto.planetapi.repository.PlanetRepository;
import io.micrometer.observation.ObservationFilter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanetService {

    private final PlanetRepository repository;

    PlanetService(PlanetRepository planetRepository) {
        this.repository = planetRepository;
    }

    public Planet create(PlanetDTO request) {
        return repository.save(new Planet(request));
    }

    public Optional<PlanetDTO> findById(Long id){
        return repository.findById(id).map(PlanetDTO::new);
    }

    public Optional<PlanetDTO> findByName(String name) {
        return repository.findByName(name).map(PlanetDTO::new);
    }
}

