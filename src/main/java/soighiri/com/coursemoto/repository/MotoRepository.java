package soighiri.com.coursemoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soighiri.com.coursemoto.model.Moto;
@Repository
public interface MotoRepository extends JpaRepository<Moto,Long> {
}
