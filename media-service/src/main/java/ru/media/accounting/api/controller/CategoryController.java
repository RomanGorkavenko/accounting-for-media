package ru.media.accounting.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.media.accounting.api.mappers.CategoryMapper;
import ru.media.accounting.dto.category.CategoryRequest;
import ru.media.accounting.dto.category.CategoryResponse;
import ru.media.accounting.service.CategoryService;
import ru.spring.boot.starter.aop.annotations.Timer;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Timer
@RequestMapping("/api/category")
@Tag(name = "Категории.", description = "API для работы с категориями.")
public class CategoryController {

    private final CategoryService service;
    private final CategoryMapper mapper;

    @CrossOrigin(origins = "http://localhost:8765")
    @GetMapping("/get/{title}")
    @Operation(summary = "Получить категорию по названию.", description = "Предоставляет категорию по названию.")
    public ResponseEntity<CategoryResponse> getCategoryByTitle(@PathVariable("title") String title) {
        return ResponseEntity.ok(mapper.toDto(service.findByTitle(title)));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @GetMapping("/all")
    @Operation(summary = "Получить все категории.", description = "Предоставляет все категории.")
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        return ResponseEntity.ok(mapper.toDto(service.findAll()));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @PostMapping()
    @Operation(summary = "Создать новую категорию.", description = "Создает новую категорию.")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request) {
        return ResponseEntity.ok(mapper.toDto(service.save(request)));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @PutMapping("/update")
    @Operation(summary = "Обновить категорию.", description = "Обновляет категорию.")
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody CategoryRequest request) {
        return ResponseEntity.ok(mapper.toDto(service.update(request)));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @DeleteMapping("/delete")
    @Operation(summary = "Удалить категорию.", description = "Удаляет категорию.")
    public ResponseEntity<String> deleteCategory(@RequestBody CategoryRequest request) {
        service.delete(request);
        return new ResponseEntity<>("Категория " + request.getTitle() + " удалена.", HttpStatus.OK);
    }
}
