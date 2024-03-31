package com.humberto.planetapi.repository;

import com.humberto.planetapi.domain.Planet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlanetRepository extends CrudRepository<Planet,Long> {

    Optional<Planet> findByName(String name);
}
