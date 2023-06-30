package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.dto.StudentDtoIn;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("student")
@Tag(name = "Контроллер по работе со студентами")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Создание студента
    @PostMapping // POST http://localhost:8080/student
    public StudentDtoOut createStudent(@RequestBody StudentDtoIn studentDtoIn) {
        return studentService.add(studentDtoIn);
    }

    // Редактирвоание студента по идентификатору
    @PutMapping("{id}") // PUT http://localhost:8080/student/1
    public StudentDtoOut editStudent(@PathVariable("id") long id, @RequestBody StudentDtoIn studentDtoIn) {
        return studentService.update(id, studentDtoIn);
    }

    // Получить студента по идентификатору
    @GetMapping("{id}") // GET http://localhost:8080/student/1
    public StudentDtoOut getStudent(@PathVariable("id") long id) {
        return studentService.get(id);
    }

    // Удаление студента по идентификатору
    @DeleteMapping("{id}") // DELETE http://localhost:8080/student/1
    public StudentDtoOut deleteStudent(@PathVariable("id") long id) {
        return studentService.delete(id);
    }

    // Фильтрация по возрасту
    @GetMapping
    public List<StudentDtoOut> findAll(@RequestParam(required = false) Integer age) {
        return studentService.findAll(age);
    }

    // Фильтрация по возрасту в промежутке
    @GetMapping("/filter")
    public List<StudentDtoOut> findByAgeBetween(@RequestParam int ageFrom, @RequestParam int ageTo) {
        return studentService.findByAgeBetween(ageFrom, ageTo);
    }

    @GetMapping("/{id}/faculty")
    public FacultyDtoOut findFaculty(@PathVariable("id") long id) {
        return studentService.findFaculty(id);
    }

}
