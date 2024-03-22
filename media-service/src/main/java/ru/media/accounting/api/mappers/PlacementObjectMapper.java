package ru.media.accounting.api.mappers;

import org.mapstruct.Mapper;
import ru.media.accounting.dto.placement_object.PlacementObjectResponse;
import ru.media.accounting.model.PlacementObject;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlacementObjectMapper {

    List<PlacementObjectResponse> toDto(List<PlacementObject> placementObjects);
    PlacementObjectResponse toDto(PlacementObject placementObject);
    PlacementObject toEntity(PlacementObjectResponse placementObjectResponse);
}
