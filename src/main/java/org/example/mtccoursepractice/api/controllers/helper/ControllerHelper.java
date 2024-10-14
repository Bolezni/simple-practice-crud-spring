package org.example.mtccoursepractice.api.controllers.helper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.mtccoursepractice.api.exceptions.NotFoundException;
import org.example.mtccoursepractice.store.entity.CourseEntity;
import org.example.mtccoursepractice.store.entity.TeacherEntity;
import org.example.mtccoursepractice.store.repository.CourseRepository;
import org.example.mtccoursepractice.store.repository.TeacherRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) //experimental
@Component
@Transactional
public class ControllerHelper {

    CourseRepository courseRepository;

    TeacherRepository teacherRepository;

    public TeacherEntity getTeacherEntityByIdOrThrowException(Long id) {
       return teacherRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Teacher with current id " + id + " not found"));
    }

    public CourseEntity getCourseEntityByIdOrThrowException(Long id) {
        return courseRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Course with current id " + id + " not found"));
    }
}
