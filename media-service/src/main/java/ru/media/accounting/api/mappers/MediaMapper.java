package ru.media.accounting.api.mappers;

import org.mapstruct.Mapper;
import ru.media.accounting.dto.MediaResponse;
import ru.media.accounting.model.Media;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MediaMapper {

    MediaResponse toDto(Media media);

    List<MediaResponse> toDto(List<Media> media);

    Media toEntity(MediaResponse mediaResponse);
}
