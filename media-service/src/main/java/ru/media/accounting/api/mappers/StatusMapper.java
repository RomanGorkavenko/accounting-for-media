package ru.media.accounting.api.mappers;

import org.mapstruct.Mapper;
import ru.media.accounting.dto.status.StatusResponse;
import ru.media.accounting.model.Status;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusMapper {

    List<StatusResponse> toDto(List<Status> statuses);
    StatusResponse toDto(Status status);
    Status toEntity(StatusResponse statusResponse);
}
