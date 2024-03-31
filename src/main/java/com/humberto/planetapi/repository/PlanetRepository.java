package com.humberto.planetapi.repository;

import com.humberto.planetapi.domain.Planet;
import org.springframework.data.repository.CrudRepository;

public interface PlanetRepository extends CrudRepository<Planet,Long> {

}
