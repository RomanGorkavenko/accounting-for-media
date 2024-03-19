package ru.media.accounting.api.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.media.accounting.api.controller.MediaController;
import ru.media.accounting.api.mappers.MediaMapper;
import ru.media.accounting.dto.MediaResponse;
import ru.media.accounting.service.MediaService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/media")
public class MediaControllerImpl implements MediaController {

    private final MediaService mediaService;
    private final MediaMapper mediaMapper;

    @Override
    @GetMapping("/user/{username}")
    public List<MediaResponse> getMediaByUserId(@PathVariable("username") String username) {
        return mediaMapper.toDto(mediaService.getByUserId(username));
    }

    @Override
    @GetMapping
    public String getMedia() {
        return "Hello";
    }


}
