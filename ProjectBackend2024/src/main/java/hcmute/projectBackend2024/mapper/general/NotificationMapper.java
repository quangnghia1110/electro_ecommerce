package hcmute.projectBackend2024.mapper.general;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.general.NotificationRequest;
import hcmute.projectBackend2024.dto.general.NotificationResponse;
import hcmute.projectBackend2024.entity.general.Notification;
import hcmute.projectBackend2024.generic.GenericMapper;
import hcmute.projectBackend2024.utils.MapperUtils;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = MapperUtils.class)
public interface NotificationMapper extends GenericMapper<Notification, NotificationRequest, NotificationResponse> {

    @Override
    @Mapping(source = "userId", target = "user")
    Notification requestToEntity(NotificationRequest request);

    @Override
    @Mapping(source = "userId", target = "user")
    Notification partialUpdate(@MappingTarget Notification entity, NotificationRequest request);

}
