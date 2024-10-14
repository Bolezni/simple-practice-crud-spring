package org.example.mtccoursepractice.api.sevice;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.mtccoursepractice.api.controllers.helper.ControllerHelper;
import org.example.mtccoursepractice.api.dto.AckDto;
import org.example.mtccoursepractice.api.dto.CourseDto;
import org.example.mtccoursepractice.api.exceptions.BadRequestException;
import org.example.mtccoursepractice.api.exceptions.NotFoundException;
import org.example.mtccoursepractice.api.factories.CourseDtoFactories;
import org.example.mtccoursepractice.store.entity.CourseEntity;
import org.example.mtccoursepractice.store.entity.TeacherEntity;
import org.example.mtccoursepractice.store.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseService {

    CourseRepository courseRepository;

    CourseDtoFactories courseDtoFactories;

    ControllerHelper controllerHelper;

    @Transactional
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(courseDtoFactories::makeCourseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CourseDto createCourse(Long teacherId, String courseName,String description) {

        TeacherEntity teacher = controllerHelper.getTeacherEntityByIdOrThrowException(teacherId);

        courseRepository.findByCourseName(courseName).ifPresent(a -> {
            throw new NotFoundException("Course with current name already exists");
        });

        if(description.isBlank()){
            throw new BadRequestException("Description cannot be blank");
        }

        CourseEntity course = CourseEntity.builder()
                .courseName(courseName)
                .description(description)
                .teacher(teacher)
                .build();

        final CourseEntity savedCourse = courseRepository.saveAndFlush(course);

        return courseDtoFactories.makeCourseDto(savedCourse);
    }

    @Transactional
    public CourseDto updateCourse(Long courseId,String description){

        CourseEntity course = controllerHelper.getCourseEntityByIdOrThrowException(courseId);

        if(description.isBlank()){
            return courseDtoFactories.makeCourseDto(course);
        }

        courseRepository.findCourseEntitiesByTeacherIdAndCourseNameIgnoreCase(
                        course.getTeacher().getId(),
                        course.getCourseName())
                .filter(anotherCourse -> !anotherCourse.getId().equals(courseId))
                .ifPresent(anotherCourse -> {
                    throw new NotFoundException("Course already exists");
                });


        course = courseRepository.saveAndFlush(course);

        return courseDtoFactories.makeCourseDto(course);
    }

    @Transactional
    public List<CourseDto> fetchCourses(Optional<String> optionalPrefixName) {

        optionalPrefixName =  optionalPrefixName.filter(prefix -> !prefix.trim().isEmpty());

        Stream<CourseEntity> entityStream = optionalPrefixName
                .map(courseRepository::streamAllByCourseNameStartsWithIgnoreCase)
                .orElseGet(courseRepository::streamAllBy);

        return  entityStream.map(courseDtoFactories::makeCourseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public AckDto deleteCourse(Long courseId){
        controllerHelper.getCourseEntityByIdOrThrowException(courseId);

        courseRepository.deleteById(courseId);

        return AckDto.builder().answer(true).build();
    }

    @Transactional
    public List<CourseDto> allCoursesByTeacherId(Long teacherId){
        controllerHelper.getTeacherEntityByIdOrThrowException(teacherId);

        List<CourseEntity> courses = courseRepository.findAllByTeacherId(teacherId)
                .orElseThrow(() -> new NotFoundException("Courses not found"));

        return courses.stream().
                map(courseDtoFactories::makeCourseDto)
                .collect(Collectors.toList());
    }
}
