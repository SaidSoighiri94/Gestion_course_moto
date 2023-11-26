package soighiri.com.coursemoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import soighiri.com.coursemoto.model.Ecurie;

import java.util.List;

public interface EcurieRepository extends JpaRepository<Ecurie,Long> {
    List<Ecurie> findByNomEcurie(String nom);
    List<Ecurie> findByNomEcurieContainsIgnoreCase(String nom);

}
