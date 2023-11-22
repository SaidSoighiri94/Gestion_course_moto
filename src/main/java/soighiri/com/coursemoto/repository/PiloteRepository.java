package soighiri.com.coursemoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import soighiri.com.coursemoto.model.Pilote;

public interface PiloteRepository extends JpaRepository<Pilote, Long> {
}
