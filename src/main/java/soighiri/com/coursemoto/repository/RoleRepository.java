package soighiri.com.coursemoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soighiri.com.coursemoto.model.Role;

public interface RoleRepository extends JpaRepository <Role,Long> {
}
