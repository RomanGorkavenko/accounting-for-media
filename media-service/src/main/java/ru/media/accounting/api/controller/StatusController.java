package ru.media.accounting.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.media.accounting.api.mappers.StatusMapper;
import ru.media.accounting.dto.status.StatusRequest;
import ru.media.accounting.dto.status.StatusResponse;
import ru.media.accounting.service.StatusService;
import ru.spring.boot.starter.aop.annotations.Timer;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Timer
@RequestMapping("/api/status")
@Tag(name = "Статусы", description = "API для работы с статусами")
public class StatusController {

    private final StatusService service;
    private final StatusMapper mapper;

    @CrossOrigin(origins = "http://localhost:8765")
    @GetMapping("/get/{title}")
    @Operation(summary = "Получить статус по названию.", description = "Предоставляет статус по названию.")
    public ResponseEntity<StatusResponse> getStatusByTitle(@PathVariable("title") String title) {
        return ResponseEntity.ok(mapper.toDto(service.findByTitle(title)));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @GetMapping("/all")
    @Operation(summary = "Получить все статусы.", description = "Предоставляет все статусы.")
    public ResponseEntity<List<StatusResponse>> getStatuses() {
        return ResponseEntity.ok(mapper.toDto(service.findAll()));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @PostMapping()
    @Operation(summary = "Создать новый статус.",
            description = "Создает новый статус. Только для администратора.")
    @PreAuthorize("@mediaServiceCustomSecurityExpression.canAccessUserROLE_ADMIN()")
    public ResponseEntity<StatusResponse> createStatus(@RequestBody StatusRequest request) {
        return ResponseEntity.ok(mapper.toDto(service.save(request)));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @PutMapping("/update")
    @Operation(summary = "Обновить статус.", description = "Обновляет статус. Только для администратора.")
    @PreAuthorize("@mediaServiceCustomSecurityExpression.canAccessUserROLE_ADMIN()")
    public ResponseEntity<StatusResponse> updateStatus(@RequestBody StatusRequest request) {
        return ResponseEntity.ok(mapper.toDto(service.update(request)));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @DeleteMapping("/delete")
    @Operation(summary = "Удалить статус.",
            description = "Удаляет статус с указанным названием. Только для администратора." +
            "Прежде чем удалять статус, необходимо проверить, что носители его не содержат.")
    @PreAuthorize("@mediaServiceCustomSecurityExpression.canAccessUserROLE_ADMIN()")
    public ResponseEntity<String> deleteStatus(@RequestBody StatusRequest request) {
        service.delete(request);
        return new ResponseEntity<>("Статус" + request.getTitle() + " удален.", HttpStatus.OK);
    }
}
