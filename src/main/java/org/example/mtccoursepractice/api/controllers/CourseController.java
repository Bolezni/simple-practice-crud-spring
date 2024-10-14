package org.example.mtccoursepractice.api.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.mtccoursepractice.api.dto.AckDto;
import org.example.mtccoursepractice.api.dto.CourseDto;
import org.example.mtccoursepractice.api.sevice.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseController {

    CourseService courseService;

    private static final String GET_COURSES = "/api/courses";
    private static final String POST_COURSE = "/api/teacher/{id}/course";
    private static final String UPDATE_COURSE = "/api/course/{id}";
    private static final String DELETE_COURSE = "/api/course/{id}";
    private static final String FETCH_COURSE = "/api/course";
    private static final String GET_ALL_COURSES = "/api/teacher/{id}/courses";


    @GetMapping(value = GET_COURSES)
    public List<CourseDto> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping(value = POST_COURSE)
    public CourseDto createCourse(@PathVariable(name = "id") Long teacherId,
                                  @RequestParam(name = "course_name", required = false) String courseName,
                                  @RequestParam(name = "course_description", required = false) String description) {

       return courseService.createCourse(teacherId, courseName, description);
    }


    @PutMapping(value = UPDATE_COURSE)
    public CourseDto updateCourse(@PathVariable(name = "id") Long courseId,
                                  @RequestParam(name = "course_description") String description){

        return courseService.updateCourse(courseId, description);
    }

    @GetMapping(value = FETCH_COURSE)
    public List<CourseDto> fetchCourses(@RequestParam(name = "prefix") Optional<String> optionalPrefixName) {

       return courseService.fetchCourses(optionalPrefixName);
    }

    @DeleteMapping(value = DELETE_COURSE)
    public AckDto deleteCourse(@PathVariable(name = "id") Long courseId){
        return courseService.deleteCourse(courseId);
    }

    @GetMapping(value = GET_ALL_COURSES)
    public List<CourseDto> allCoursesByTeacherId(@PathVariable(name = "id") Long teacherId){
        return courseService.allCoursesByTeacherId(teacherId);
    }
}
