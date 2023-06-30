package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDtoIn;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("faculty")
@Tag(name = "Контроллер по работе с факультетами")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    // Создание факультета
    @PostMapping // POST http://localhost:8080/faculty
    public FacultyDtoOut createFaculty(@RequestBody FacultyDtoIn facultyDtoIn) {
        return facultyService.add(facultyDtoIn);
    }

    // Редактирвоание студента по идентификатору
    @PutMapping("{id}") // PUT http://localhost:8080/faculty/1
    public FacultyDtoOut editFaculty(@PathVariable("id") long id, @RequestBody FacultyDtoIn facultyDtoIn) {
        return facultyService.update(id, facultyDtoIn);
    }

    // Получить факультета по идентификатору
    @GetMapping("{id}") // GET http://localhost:8080/faculty/1
    public FacultyDtoOut getFaculty(@PathVariable("id") long id) {
        return facultyService.get(id);
    }

    // Удаление факультета по идентификатору
    @DeleteMapping("{id}") // DELETE http://localhost:8080/faculty/1
    public FacultyDtoOut deleteFaculty(@PathVariable("id") long id) {
        return facultyService.delete(id);
    }

    // Фильтрация по цвету
    @GetMapping
    public List<FacultyDtoOut> findAll(@RequestParam(required = false) String color) {
        return facultyService.findAll(color);
    }

    // Фильтрация по цвету или имени
    @GetMapping("/filter")
    public List<FacultyDtoOut> findByColorOrName(@RequestParam String colorOrName) {
        return facultyService.findByColorOrName(colorOrName);
    }

    @GetMapping("/{id}/student")
    public List<StudentDtoOut> findStudents(@PathVariable("id") long id) {
        return facultyService.findStudents(id);
    }

}
