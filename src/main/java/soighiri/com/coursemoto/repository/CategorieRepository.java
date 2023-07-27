package soighiri.com.coursemoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soighiri.com.coursemoto.model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
