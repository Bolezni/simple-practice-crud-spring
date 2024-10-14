package org.example.mtccoursepractice.api.factories;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.mtccoursepractice.api.dto.TeacherDto;
import org.example.mtccoursepractice.store.entity.TeacherEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Component
public class TeacherDtoFactories {

    CourseDtoFactories courseDtoFactories;

    public TeacherDto makeTeacherDto(TeacherEntity teacher) {
        return TeacherDto.builder()
                .name(teacher.getName())
                .courses(teacher.getCourses()
                        .stream()
                        .map(courseDtoFactories::makeCourseDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
