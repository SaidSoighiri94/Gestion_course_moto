package soighiri.com.coursemoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soighiri.com.coursemoto.model.Email;

public interface EmailRepository extends JpaRepository<Email,Long> {
}
