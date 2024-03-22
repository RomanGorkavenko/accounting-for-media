package ru.media.accounting.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.media.accounting.api.mappers.PlacementObjectMapper;
import ru.media.accounting.dto.placement_object.PlacementObjectRequest;
import ru.media.accounting.dto.placement_object.PlacementObjectResponse;
import ru.media.accounting.service.PlacementObjectService;
import ru.spring.boot.starter.aop.annotations.Timer;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Timer
@RequestMapping("/api/placement-object")
@Tag(name = "Объект размещения", description = "API для работы с объектами размещения")
public class PlacementObjectController {

    private final PlacementObjectService service;
    private final PlacementObjectMapper mapper;

    @CrossOrigin(origins = "http://localhost:8765")
    @GetMapping("/get/{title}")
    @Operation(summary = "Получить объект размещения по названию.",
            description = "Предоставляет объект размещения по названию.")
    public ResponseEntity<PlacementObjectResponse> getPlacementObjectByTitle(@PathVariable("title") String title) {
        return ResponseEntity.ok(mapper.toDto(service.findByTitle(title)));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @GetMapping("/all")
    @Operation(summary = "Получить объекты размещения.", description = "Предоставляет объекты размещения.")
    public ResponseEntity<List<PlacementObjectResponse>> getPlacementObjects() {
        return ResponseEntity.ok(mapper.toDto(service.findAll()));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @PostMapping()
    @Operation(summary = "Создать новый объект размещения.",
            description = "Создает новый объект размещения. Только для администратора.")
    @PreAuthorize("@mediaServiceCustomSecurityExpression.canAccessUserROLE_ADMIN()")
    public ResponseEntity<PlacementObjectResponse> createPlacementObject(@RequestBody PlacementObjectRequest request) {
        return ResponseEntity.ok(mapper.toDto(service.save(request)));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @PutMapping("/update")
    @Operation(summary = "Обновить объект размещения.",
            description = "Обновляет объект размещения. Только для администратора.")
    @PreAuthorize("@mediaServiceCustomSecurityExpression.canAccessUserROLE_ADMIN()")
    public ResponseEntity<PlacementObjectResponse> updatePlacementObject(@RequestBody PlacementObjectRequest request) {
        return ResponseEntity.ok(mapper.toDto(service.update(request)));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @DeleteMapping("/delete")
    @Operation(summary = "Удалить объект размещения.",
            description = "Удаляет объект размещения с указанным названием. Только для администратора." +
            "Прежде чем удалять статус, необходимо проверить, что носители его не содержат.")
    @PreAuthorize("@mediaServiceCustomSecurityExpression.canAccessUserROLE_ADMIN()")
    public ResponseEntity<String> deletePlacementObject(@RequestBody PlacementObjectRequest request) {
        service.delete(request);
        return new ResponseEntity<>("Объект размещения" + request.getTitle() + " удален.", HttpStatus.OK);
    }
}
