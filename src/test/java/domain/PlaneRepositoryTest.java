package domain;

import com.humberto.planetapi.domain.Planet;
import com.humberto.planetapi.repository.PlanetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static com.humberto.planetapi.common.PlanetCommonsTest.PLANET_DTO;
import static com.humberto.planetapi.common.PlanetCommonsTest.PLANET_INVALID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
public class PlaneRepositoryTest {

    @Autowired
    private PlanetRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Configuration
    @ComponentScan("com.humberto.planetapi")
    public static class AppConfig { //Included because Spring could not inject Repository
    }

    @Test
    void shouldCreatePlanetWithValidData(){
        Planet planet = repository.save(new Planet(PLANET_DTO));

        Planet planetFromDB = entityManager.find(Planet.class,planet.getId());

        assertThat(planetFromDB).isNotNull();
        assertThat(planetFromDB.getName()).isEqualTo(planet.getName());
        assertThat(planetFromDB.getClimate()).isEqualTo(planet.getClimate());
        assertThat(planetFromDB.getTerrain()).isEqualTo(planet.getTerrain());
    }

    @Test
    void shouldThrowExceptionWhenCreatePlanetWithInvalidData(){
        Planet empty = new Planet();

        assertThatThrownBy(()->repository.save(empty));
        assertThatThrownBy(()->repository.save(PLANET_INVALID));
    }

}
