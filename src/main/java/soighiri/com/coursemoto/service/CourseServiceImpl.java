package soighiri.com.coursemoto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soighiri.com.coursemoto.dto.CourseDto;
import soighiri.com.coursemoto.model.Course;
import soighiri.com.coursemoto.model.Circuit;
import soighiri.com.coursemoto.repository.CourseRepository;
import soighiri.com.coursemoto.service.CircuitService;

import java.sql.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;
    private CircuitService circuitService;
    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, CircuitService circuitService) {
        this.courseRepository = courseRepository;
        this.circuitService = circuitService;
    }




    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course saveCourseFromCourseDto(CourseDto courseDto) {
        Course course = convertDtoToEntity(courseDto);
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course updateCourseFromCourseDto(CourseDto courseDto) {
        Course course= convertDtoToEntity(courseDto);
        return courseRepository.save(course);

        /*if (existingCourse != null) {
            // Mettez à jour les propriétés de l'entité avec les valeurs du DTO
            existingCourse.setDateCourse(courseDto.getDateCourse());
            existingCourse.setHeureCourse(courseDto.getHeureCourse());
            existingCourse.setNomCourse(courseDto.getNomCourse());
            existingCourse.setNombreTour(courseDto.getNombreTour());

            // Mettez à jour la relation avec le circuit
            Circuit circuit = circuitService.getCircuitById(courseDto.getCircuit().getIdCircuit());
            existingCourse.setCircuit(circuit);

            return courseRepository.save(existingCourse);
        } else {
            // Gérer le cas où la course n'est pas trouvée
            return null;
        }*/
    }

    @Override
    public CourseDto convertEntityToDto(Course course) {
        CourseDto courseDto = new CourseDto();
        if (course != null){
        courseDto.setIdCourse(course.getIdCourse());
        courseDto.setDateCourse(course.getDateCourse());
        courseDto.setHeureCourse(course.getHeureCourse());
        courseDto.setNomCourse(course.getNomCourse());
        courseDto.setNombreTour(course.getNombreTour());
        courseDto.setCircuit(course.getCircuit());
        }

        return courseDto;
    }

    private Course convertDtoToEntity(CourseDto courseDto) {
        Course course = new Course();
        if (courseDto != null) {
            course.setIdCourse(courseDto.getIdCourse());
            course.setDateCourse(courseDto.getDateCourse());
            course.setHeureCourse(courseDto.getHeureCourse());
            course.setNomCourse(courseDto.getNomCourse());
            course.setNombreTour(courseDto.getNombreTour());
        }
        return course;
    }
}
