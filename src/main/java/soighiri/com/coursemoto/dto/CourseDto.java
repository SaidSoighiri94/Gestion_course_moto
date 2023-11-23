package soighiri.com.coursemoto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private Long idCourse;
    private Date dateCourse;
    private String heureCourse;
    private String nomCourse;
    private int nombreTour;
}
