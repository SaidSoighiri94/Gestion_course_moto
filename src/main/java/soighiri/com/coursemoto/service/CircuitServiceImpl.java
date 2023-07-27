package soighiri.com.coursemoto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soighiri.com.coursemoto.dto.CircuitDto;
import soighiri.com.coursemoto.model.Circuit;
import soighiri.com.coursemoto.repository.CircuitRepository;

import java.util.List;

@Service
public class CircuitServiceImpl implements CircuitService {

    private CircuitRepository circuitRepository;

    @Autowired
    public CircuitServiceImpl(CircuitRepository circuitRepository) {
        this.circuitRepository = circuitRepository;
    }

    @Override
    public Circuit saveCircuitFromCircuitDto(CircuitDto circuitDto) {
        Circuit circuit = convertDtoToEntity(circuitDto);
        return circuitRepository.save(circuit);
    }

    @Override
    public Circuit updateCircuitFromCircuitDto(CircuitDto circuitDto) {
        Circuit circuit = convertDtoToEntity(circuitDto);
        return circuitRepository.save(circuit);
    }

    @Override
    public void deleteCircuitById(Long idCircuit) {
        circuitRepository.deleteById(idCircuit);
    }

    @Override
    public Circuit getCircuit(Long idCircuit) {
        return circuitRepository.findById(idCircuit).orElse(null);
    }

    @Override
    public List<Circuit> getAllCircuits() {
        return circuitRepository.findAll();
    }

    @Override
    public CircuitDto convertEntityToDto(Circuit circuit) {
        CircuitDto circuitDto = new CircuitDto();
        if (circuit != null) {
            circuitDto.setIdCircuit(circuit.getIdCircuit());
            circuitDto.setNomCircuit(circuit.getNomCircuit());
            circuitDto.setAdresseCircuit(circuit.getAdresseCircuit());
            circuitDto.setLongueur(circuit.getLongueur());
        }
        return circuitDto;
    }

    private Circuit convertDtoToEntity(CircuitDto circuitDto) {
        Circuit circuit = new Circuit();
        circuit.setIdCircuit(circuitDto.getIdCircuit());
        circuit.setNomCircuit(circuitDto.getNomCircuit());
        circuit.setAdresseCircuit(circuitDto.getAdresseCircuit());
        circuit.setLongueur(circuitDto.getLongueur());
        return circuit;
    }
}
