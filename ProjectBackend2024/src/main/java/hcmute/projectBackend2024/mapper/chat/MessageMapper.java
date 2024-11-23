package hcmute.projectBackend2024.mapper.chat;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.chat.MessageRequest;
import hcmute.projectBackend2024.dto.chat.MessageResponse;
import hcmute.projectBackend2024.entity.chat.Message;
import hcmute.projectBackend2024.generic.GenericMapper;
import hcmute.projectBackend2024.utils.MapperUtils;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = MapperUtils.class)
public interface MessageMapper extends GenericMapper<Message, MessageRequest, MessageResponse> {

    @Override
    @Mapping(source = "userId", target = "user")
    @Mapping(source = "roomId", target = "room")
    Message requestToEntity(MessageRequest request);

}
