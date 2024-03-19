package ru.media.accounting.api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import ru.media.accounting.dto.MediaResponse;

import java.util.List;

public interface MediaController {

    List<MediaResponse> getMediaByUserId(String username);

    MediaResponse getMediaByNumber(Long number);


}
