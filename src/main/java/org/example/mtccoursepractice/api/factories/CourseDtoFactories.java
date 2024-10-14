package org.example.mtccoursepractice.api.factories;

import org.example.mtccoursepractice.api.dto.CourseDto;
import org.example.mtccoursepractice.store.entity.CourseEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseDtoFactories {

    public CourseDto makeCourseDto(CourseEntity course) {
        return CourseDto.builder()
                .courseName(course.getCourseName())
                .description(course.getDescription())
                .build();
    }
}
