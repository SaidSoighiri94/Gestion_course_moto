package soighiri.com.coursemoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soighiri.com.coursemoto.model.Utilisateur;

public interface UtilsateurRepository extends JpaRepository <Utilisateur,Long> {
    Utilisateur findByEmail(String email);
}
