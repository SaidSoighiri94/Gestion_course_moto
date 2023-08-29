package soighiri.com.coursemoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soighiri.com.coursemoto.model.Ecurie;

public interface EcurieRepository extends JpaRepository<Ecurie,Long> {
}
