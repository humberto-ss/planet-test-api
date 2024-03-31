package com.humberto.planetapi.common;

import com.humberto.planetapi.domain.Planet;
import com.humberto.planetapi.domain.PlanetDTO;

public class PlanetCommonsTest {
    public static final PlanetDTO PLANET_DTO = new PlanetDTO("Earth","Green","Acceptable");
    public static final Planet PLANET_INVALID = new Planet(0l,"","","");
    public static final Planet PLANET_VALID = new Planet(1l,"Jupiter","inhospitable","Gas");
    public static final Planet PLANET_FROM_DTO = new Planet(PLANET_DTO);

}
