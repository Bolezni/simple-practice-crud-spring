package org.example.mtccoursepractice.store.repository;

import org.example.mtccoursepractice.store.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    Optional<CourseEntity> findByCourseName(String courseName);

    Optional<CourseEntity> findCourseEntitiesByTeacherIdAndCourseNameIgnoreCase(Long id, String courseName);

    Stream<CourseEntity> streamAllByCourseNameStartsWithIgnoreCase(String prefix);

    Stream<CourseEntity> streamAllBy();

   Optional<List<CourseEntity>> findAllByTeacherId(Long id);
}
