package com.humberto.planetapi.domain;

public record PlanetDTO(String name, String terrain, String climate) {

    public PlanetDTO(Planet planet){
        this(planet.getName(), planet.getTerrain(), planet.getClimate());
    }
}
