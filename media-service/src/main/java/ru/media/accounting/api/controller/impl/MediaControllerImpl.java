package ru.media.accounting.api.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring.boot.aop.annotations.Timer;
import ru.media.accounting.api.controller.MediaController;
import ru.media.accounting.api.mappers.MediaMapper;
import ru.media.accounting.dto.MediaResponse;
import ru.media.accounting.service.MediaService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Timer
@RequestMapping("/api/media")
public class MediaControllerImpl implements MediaController {

    private final MediaService mediaService;
    private final MediaMapper mediaMapper;

    @Override
    @CrossOrigin(origins = "http://localhost:8765")
    @GetMapping("/user/{username}")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#username)")
    public List<MediaResponse> getMediaByUserId(@PathVariable("username") String username) {
        return mediaMapper.toDto(mediaService.findByUsername(username));
    }

    @Override
    public MediaResponse getMediaByNumber(Long number) {
        return null;
    }


}
