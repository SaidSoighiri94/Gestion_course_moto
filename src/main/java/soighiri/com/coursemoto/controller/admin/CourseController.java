package soighiri.com.coursemoto.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import soighiri.com.coursemoto.dto.CourseDto;
import soighiri.com.coursemoto.model.Course;
import soighiri.com.coursemoto.service.CourseService;

import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping(value="/admin")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "/course/listeCourses")
    public String listCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "admin/course/listCourses";
    }

    @GetMapping(value = "/detail/{idCourse}")
    public String getCourseById(@PathVariable Long idCourse, Model model) {
        Course course = courseService.getCourseById(idCourse);
        if (course == null) {
            return "error/notFound";
        }
        model.addAttribute("course", course);
        return "course/courseDetail";
    }

    @GetMapping(value = "/delete/{idCourse}")
    public String deleteCourseById(@PathVariable Long idCourse) {
        courseService.deleteCourseById(idCourse);
        return "redirect:/admin/course/listeCourses";
    }

    @GetMapping(value = "/course/create")
    public String create(Model model) {
        model.addAttribute("courseDto", new CourseDto());
        return "admin/course/create";
    }

    @PostMapping(value = "course/create")
    public String store(@ModelAttribute("courseDto") @Valid CourseDto courseDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/course/create";
        }
        courseService.saveCourseFromCourseDto(courseDto);
        return "redirect:/admin/course/listeCourses";
    }

    @GetMapping(value = "/edit/{idCourse}")
    public String editCourse(@PathVariable Long idCourse, Model model) {
        Course course = courseService.getCourseById(idCourse);
        if (course == null) {
            return "error/notFound";
        }
        CourseDto courseDto = courseService.convertEntityToDto(course);
        model.addAttribute("courseDto", courseDto);
        return "course/edit";
    }

    @PostMapping(value = "/edit")
    public String updateCourse(@ModelAttribute @Valid CourseDto courseDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "course/edit";
        }
        courseService.updateCourseFromCourseDto(courseDto);
        return "redirect:/course/listeCourses";
    }
}
