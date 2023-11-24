package soighiri.com.coursemoto.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import soighiri.com.coursemoto.model.Circuit;
import soighiri.com.coursemoto.model.Course;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private Long idCourse;

    @NotNull(message = "La date de la course est requise")
    private Date dateCourse;

    @NotBlank(message = "L'heure de la course est requise")
    private String heureCourse;

    @NotBlank(message = "Le nom de la course est requis")
    @Size(min = 1, max = 150, message = "Le nom doit être entre 1 et 150 caractères")
    private String nomCourse;

    @Min(value = 1, message = "Le nombre de tours doit être au moins 1")
    private int nombreTour;
    private Circuit circuit;
}

