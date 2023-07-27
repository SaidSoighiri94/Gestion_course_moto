package soighiri.com.coursemoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soighiri.com.coursemoto.model.Circuit;

public interface CircuitRepository extends JpaRepository<Circuit,Long> {
    Circuit findByNomCircuitLikeIgnoreCase(String nomCircuit);

}
