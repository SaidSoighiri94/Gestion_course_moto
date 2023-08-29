package soighiri.com.coursemoto.service;

import soighiri.com.coursemoto.dto.CircuitDto;
import soighiri.com.coursemoto.model.Circuit;

import java.util.List;

public interface CircuitService {

    // Sauvegarde un circuit en utilisant un CircuitDto

    Circuit saveCircuitFromCircuitDto(CircuitDto circuitDto);

    // Met à jour un circuit en utilisant un CircuitDto
    Circuit updateCircuitFromCircuitDto(CircuitDto circuitDto);

    // Supprime un circuit de la base de données en fonction de son ID
    void deleteCircuitById(Long idCircuit);

    // Récupère un circuit en fonction de son ID
    Circuit getCircuit(Long idCircuit);

    // Récupère la liste de tous les circuits
    List<Circuit> getAllCircuits();

    // Convertir un objet Circuit en CircuitDto
    CircuitDto convertEntityToDto(Circuit circuit);

}
