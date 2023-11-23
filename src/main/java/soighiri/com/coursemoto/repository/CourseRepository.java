package soighiri.com.coursemoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soighiri.com.coursemoto.model.Course;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
