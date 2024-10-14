package org.example.mtccoursepractice.store.repository;

import org.example.mtccoursepractice.store.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
}
