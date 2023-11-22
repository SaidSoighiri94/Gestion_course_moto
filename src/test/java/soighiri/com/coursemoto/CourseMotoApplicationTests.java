package soighiri.com.coursemoto;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import soighiri.com.coursemoto.dto.CircuitDto;
import soighiri.com.coursemoto.model.Circuit;
import soighiri.com.coursemoto.repository.CircuitRepository;
import soighiri.com.coursemoto.service.CircuitServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CircuitServiceImplTest {

    // Crée un mock pour le CircuitRepository
    @Mock
    private CircuitRepository circuitRepository;

    // Injecte les mocks annotés dans CircuitServiceImpl
    @InjectMocks
    private CircuitServiceImpl circuitService;

    // Teste la méthode saveCircuitFromCircuitDto
    @Test
    void testSaveCircuitFromCircuitDto() {
        // Arrange
        CircuitDto circuitDto = new CircuitDto(1L,"Circuit de Test", "Adresse de Test", 10.0);
        Circuit expectedCircuit = new Circuit(2L,"Circuit de Test", "Adresse de Test", 10.0);
        when(circuitRepository.save(Mockito.any())).thenReturn(expectedCircuit);

        // Act
        Circuit savedCircuit = circuitService.saveCircuitFromCircuitDto(circuitDto);

        // Assert
        assertEquals(expectedCircuit, savedCircuit);
        verify(circuitRepository).save(Mockito.any());
    }

    // Teste la méthode updateCircuitFromCircuitDto
    @Test
    void testUpdateCircuitFromCircuitDto() {
        // Arrange
        CircuitDto circuitDto = new CircuitDto(1L,"Circuit de Teste", "Adresse de Teste", 10.0);
        Circuit existingCircuit = new Circuit(2L,"Circuit existant", "Adresse existante", 5.0);
        Circuit updatedCircuit = new Circuit(1L,"Circuit de Test", "Adresse de Test", 10.0);
        when(circuitRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(existingCircuit));
        when(circuitRepository.save(Mockito.any())).thenReturn(updatedCircuit);

        // Act
        Circuit result = circuitService.updateCircuitFromCircuitDto(circuitDto);

        // Assert
        assertEquals(updatedCircuit, result);
        verify(circuitRepository).findById(Mockito.anyLong());
        verify(circuitRepository).save(Mockito.any());
    }

    // Teste la méthode deleteCircuitById
    @Test
    void testDeleteCircuitById() {
        // Arrange
        Long idCircuit = 1L;

        // Act
        circuitService.deleteCircuitById(idCircuit);

        // Assert
        verify(circuitRepository).deleteById(idCircuit);
    }

    // Teste la méthode getCircuit
    @Test
    void testGetCircuit() {
        // Arrange
        Long idCircuit = 1L;
        Circuit expectedCircuit = new Circuit(1L,"Circuit de Test", "Adresse de Test", 10.0);
        when(circuitRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(expectedCircuit));

        // Act
        Circuit result = circuitService.getCircuit(idCircuit);

        // Assert
        assertEquals(expectedCircuit, result);
        verify(circuitRepository).findById(Mockito.anyLong());
    }

    // Teste la méthode getAllCircuits
    @Test
    void testGetAllCircuits() {
        // Arrange
        List<Circuit> circuits = new ArrayList<>();
        circuits.add(new Circuit(2L,"Circuit 1", "Adresse 1", 5.0));
        circuits.add(new Circuit(2L,"Circuit 2", "Adresse 2", 8.0));
        when(circuitRepository.findAll()).thenReturn(circuits);

        // Act
        List<Circuit> result = circuitService.getAllCircuits();

        // Assert
        assertEquals(circuits, result);
        verify(circuitRepository).findAll();
    }

}
