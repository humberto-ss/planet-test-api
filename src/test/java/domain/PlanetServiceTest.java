package domain;


import com.humberto.planetapi.domain.Planet;
import com.humberto.planetapi.domain.PlanetDTO;
import com.humberto.planetapi.infra.query.QueryBuilder;
import com.humberto.planetapi.repository.PlanetRepository;
import com.humberto.planetapi.service.PlanetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.humberto.planetapi.common.PlanetCommonsTest.PLANET_DTO;
import static com.humberto.planetapi.common.PlanetCommonsTest.PLANET_FROM_DTO;
import static com.humberto.planetapi.common.PlanetCommonsTest.PLANET_INVALID;
import static com.humberto.planetapi.common.PlanetCommonsTest.PLANET_VALID;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
//@SpringBootTest(classes = PlanetService.class) //Start all the Spring to be able to use PlanetService only
@ExtendWith(MockitoExtension.class) // Use only mockito to proceed with Unit Tests without be necessary Spring Framework
public class PlanetServiceTest {


    //@Autowired //cannot be used because depends to inject the repository too
    @InjectMocks //inject the bean and also inject all the dependencies as mocks
    PlanetService service;

    //@MockBean // Spring mock all the calls to Repository
    @Mock
    private PlanetRepository repository;

   @Test
    void shouldCreateAPlanet(){
       //Arrange
         when(repository.save(PLANET_FROM_DTO)).thenReturn(PLANET_FROM_DTO); //Tell to mockito that when Save is called then return the expected entity
       //Act
         Planet planet = service.create(PLANET_DTO);
       //Assert
         assertThat(planet).isEqualTo(PLANET_FROM_DTO);
    }
    @Test
    void shouldThrowExceptionWhenInvalidCreateData(){
       when(repository.save(PLANET_INVALID)).thenThrow(RuntimeException.class);

       assertThatThrownBy(()-> service.create(new PlanetDTO(PLANET_INVALID))).isInstanceOf(RuntimeException.class);
   }
    @Test
    void shouldReturnPlanetById(){
        //Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(PLANET_VALID));
        //Act
        Optional<PlanetDTO> opt = service.findById(1L);
        //Assert
        assertThat(opt.isPresent()).isTrue();
    }
    @Test
    void shouldReturnPlanetByName(){
        //Arrange
        String name = "Earth";
        when(repository.findByName(name)).thenReturn(Optional.of(PLANET_FROM_DTO));
        //Act
        Optional<PlanetDTO> opt = service.findByName(name);
        //Assert
        assertThat(opt.isPresent()).isTrue();
        assertThat(opt.get().name()).isEqualTo(name);
    }
   @Test
    void shouldReturnEmptyWhenPlanetNotExist(){
       //Arrange
       String name = "Not Exists";
       when(repository.findById(99L)).thenReturn(Optional.empty());
       when(repository.findByName(name)).thenReturn(Optional.empty());
       //Act
       Optional<PlanetDTO> planetID =  service.findById(99L);
       Optional<PlanetDTO> planetName = service.findByName(name);
       //Assert
       assertThat(planetID).isEmpty();
       assertThat(planetName).isEmpty();
   }
   @Test
    void shouldNotReturnPlanets(){
       //Arrange
       Example<Planet> query = QueryBuilder.makeQuery(new Planet());
       when(repository.findAll(query)).thenReturn(Collections.emptyList());
       //Act
       List<PlanetDTO> planets = service.list(null,null);
       //Assert
       assertThat(planets).isEmpty();
   }
   @Test
    void shouldReturnAllPlanets(){
       //TODO
   }

   @Test
    void shouldNotThrowExceptionWhenDeletePlanetById(){
      assertThatCode(()->service.remove(1L)).doesNotThrowAnyException();
   }
   @Test
    void shouldThrowExecptionWhenDeleteByIdNotFound(){
       doThrow(RuntimeException.class).when(repository).deleteById(1L);
       assertThatThrownBy(()->service.remove(1L)).isInstanceOf(RuntimeException.class);
   }

}
