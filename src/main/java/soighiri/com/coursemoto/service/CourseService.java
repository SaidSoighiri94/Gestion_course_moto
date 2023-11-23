package soighiri.com.coursemoto.service;
import soighiri.com.coursemoto.dto.CourseDto;
import soighiri.com.coursemoto.model.Course;
import java.util.List;

public interface CourseService {
    // Affiher une moto par son Id
    Course getCourseById(Long id);

    //lister les courses
    List<Course> getAllCourses();

    Course saveCourseFromCourseDto(CourseDto courseDto);
    void deleteCourseById(Long id);

    //Modifier Une moto en utilisant un dto
    Course updateMotoFromCourseDto(CourseDto courseDto);

    // Convertir l'entity en DTO
    CourseDto convertEntityToDto(Course course);
}
