package org.example.mtccoursepractice.api.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.mtccoursepractice.api.dto.AckDto;
import org.example.mtccoursepractice.api.dto.TeacherDto;
import org.example.mtccoursepractice.api.sevice.TeacherService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeacherController {

    TeacherService teacherService;

    private static final String CREATE_TEACHER = "/api/teacher";
    private static final String GET_TEACHER = "/api/teacher/{id}";
    private static final String UPDATE_TEACHER = "/api/teacher/{id}";
    private static final String DELETE_TEACHER = "/api/teacher/{id}";



    @GetMapping(value = GET_TEACHER)
    public TeacherDto getTeacher(@PathVariable Long id) {
        return teacherService.getTeacher(id);
    }

    @PostMapping(value = CREATE_TEACHER)
    public TeacherDto createTeacher(@RequestParam String name){

        return teacherService.createTeacher(name);
    }

    @PutMapping(value = UPDATE_TEACHER)
    public TeacherDto updateTeacher(@PathVariable Long id,
                                    @RequestParam(name = "teacher_name") String name){
        return teacherService.updateTeacher(id, name);
    }

    @DeleteMapping(value = DELETE_TEACHER)
    public AckDto deleteTeacher(@PathVariable Long id){
       return teacherService.deleteTeacher(id);
    }
}
