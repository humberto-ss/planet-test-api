package com.humberto.planetapi.domain;

public record PlanetCreateDTO(String name, String terrain, String climate) {

    public PlanetCreateDTO(Planet planet){
        this(planet.getName(), planet.getTerrain(), planet.getClimate());
    }
}
