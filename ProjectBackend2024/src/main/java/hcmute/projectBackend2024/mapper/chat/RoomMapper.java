package hcmute.projectBackend2024.mapper.chat;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.chat.RoomRequest;
import hcmute.projectBackend2024.dto.chat.RoomResponse;
import hcmute.projectBackend2024.entity.chat.Room;
import hcmute.projectBackend2024.generic.GenericMapper;
import hcmute.projectBackend2024.utils.MapperUtils;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = MapperUtils.class)
public interface RoomMapper extends GenericMapper<Room, RoomRequest, RoomResponse> {

    @Override
    @Mapping(source = "userId", target = "user")
    Room requestToEntity(RoomRequest request);

}
