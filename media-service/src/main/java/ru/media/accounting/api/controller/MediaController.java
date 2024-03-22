package ru.media.accounting.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.media.accounting.dto.MediaRequest;
import ru.media.accounting.dto.MediaRequestUpdate;
import ru.spring.boot.starter.aop.annotations.Timer;
import ru.media.accounting.api.mappers.MediaMapper;
import ru.media.accounting.dto.MediaResponse;
import ru.media.accounting.service.MediaService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Timer
@RequestMapping("/api/media")
@Tag(name = "Носители информации.", description = "API для работы с носителями информации.")
public class MediaController {

    private final MediaService mediaService;
    private final MediaMapper mediaMapper;

    @CrossOrigin(origins = "http://localhost:8765")
    @GetMapping("/get-title/{title}")
    @Operation(summary = "Получить носитель по названию.", description = "Предоставляет носитель по названию.")
    public ResponseEntity<List<MediaResponse>> getMediaByTitle(@PathVariable("title") String mediaTitle) {
        return ResponseEntity.ok(mediaMapper.toDto(mediaService.findByTitle(mediaTitle)));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @GetMapping("/get-number/{number}")
    @Operation(summary = "Получить носитель по номеру.", description = "Предоставляет носитель по номеру.")
    public ResponseEntity<MediaResponse> getMediaByNumber(@PathVariable("number") Long number) {
        return ResponseEntity.ok(mediaMapper.toDto(mediaService.findByNumber(number)));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @PostMapping()
    @Operation(summary = "Добавить носитель.", description = "Добавляет носитель.")
    public ResponseEntity<MediaResponse> save(@RequestBody MediaRequest mediaRequest) {
        return ResponseEntity.ok(mediaMapper.toDto(mediaService.add(mediaRequest)));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @PutMapping("/update")
    @Operation(summary = "Обновить носитель.", description = "Обновляет носитель.")
    public ResponseEntity<MediaResponse> update(@RequestBody MediaRequestUpdate mediaRequestUpdate) {
        return ResponseEntity.ok(mediaMapper.toDto(mediaService.update(mediaRequestUpdate)));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @PutMapping("/update/{number}/{username}")
    @Operation(summary = "Передать носитель другому пользователю.",
            description = "Передает носитель другому пользователю. Только для администратора.")
    @PreAuthorize("@mediaServiceCustomSecurityExpression.canAccessUserROLE_ADMIN()")
    public ResponseEntity<MediaResponse> changeUser(@PathVariable("number") Long number,
                                                @PathVariable("username") String username) {
        return ResponseEntity.ok(mediaMapper.toDto(mediaService.changeUser(number, username)));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @DeleteMapping("/delete/{number}")
    @Operation(summary = "Удалить носитель.", description = "Удаляет носитель.")
    public ResponseEntity<String> delete(@PathVariable("number") Long number) {
        mediaService.delete(number);
        return new ResponseEntity<>("Носитель с number = \"" + number + "\" успешно удален.", HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @GetMapping("/user/{username}")
    @Operation(summary = "Получить все носители пользователя по его имени.",
                description = "Предоставляет все носители пользователя по его имени. " +
                        "Предоставляет все носители любого пользователя по его имени - только для администратора.")
    public List<MediaResponse> getMediaByUserId(@PathVariable("username")
                                                @Parameter(name = "username", description = "username пользователя",
                                                examples = {@ExampleObject(name = "Администратор", value = "admin",
                                                description = "Найти пользователя с username \"admin\""),
                                                @ExampleObject(name = "Пользователь", value = "user",
                                                description = "Найти пользователя с username \"user\"")}
                                                ) String username ) {
        return mediaMapper.toDto(mediaService.findByUsername(username));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @GetMapping("/all")
    @PreAuthorize("@mediaServiceCustomSecurityExpression.canAccessUserROLE_ADMIN()")
    @Operation(summary = "Получить все носители.",
            description = "Предоставляет все носители. Только для администратора.")
    public List<MediaResponse> getAll() {
        return mediaMapper.toDto(mediaService.findAll());
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @GetMapping("/user/all")
    @Operation(summary = "Получить все носители пользователя.",
            description = "Предоставляет все носители пользователя.")
    public List<MediaResponse> getAllByUser() {
        return mediaMapper.toDto(mediaService.findAllByUser());
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @GetMapping("/category/{categoryname}")
    @Operation(summary = "Получить все носители пользователя по категории с сортировкой по статусу.",
            description = "Предоставляет все носители пользователя по категории с сортировкой по статусу.")
    public List<MediaResponse> getAllByCategoryAndUser(@PathVariable("categoryname") String category) {
        return mediaMapper.toDto(mediaService.findAllByCategoryAndUser(category));
    }


}
