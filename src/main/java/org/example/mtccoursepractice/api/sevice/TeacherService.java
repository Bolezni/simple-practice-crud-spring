package org.example.mtccoursepractice.api.sevice;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.mtccoursepractice.api.controllers.helper.ControllerHelper;
import org.example.mtccoursepractice.api.dto.AckDto;
import org.example.mtccoursepractice.api.dto.TeacherDto;
import org.example.mtccoursepractice.api.exceptions.BadRequestException;
import org.example.mtccoursepractice.api.factories.TeacherDtoFactories;
import org.example.mtccoursepractice.store.entity.TeacherEntity;
import org.example.mtccoursepractice.store.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeacherService {
    TeacherRepository teacherRepository;

    ControllerHelper controllerHelper;

    TeacherDtoFactories teacherDtoFactories;

    @Transactional
    public TeacherDto getTeacher(Long id) {
        TeacherEntity teacher = controllerHelper.getTeacherEntityByIdOrThrowException(id);

        return teacherDtoFactories.makeTeacherDto(teacher);
    }

    @Transactional
    public TeacherDto createTeacher(String name){

        TeacherEntity teacher = TeacherEntity.builder()
                .name(name)
                .build();

        teacher = teacherRepository.saveAndFlush(teacher);

        return teacherDtoFactories.makeTeacherDto(teacher);
    }

    @Transactional
    public TeacherDto updateTeacher(Long id, String name){
        TeacherEntity teacher = controllerHelper.getTeacherEntityByIdOrThrowException(id);

        if(name.isBlank()){
            throw new BadRequestException("Teacher name cannot be blank");
        }

        teacher.setName(name);

        teacher = teacherRepository.saveAndFlush(teacher);

        return teacherDtoFactories.makeTeacherDto(teacher);
    }

    @Transactional
    public AckDto deleteTeacher(Long id){
        TeacherEntity teacher = controllerHelper.getTeacherEntityByIdOrThrowException(id);

        teacherRepository.delete(teacher);

        return AckDto.builder().answer(true).build();
    }
}
