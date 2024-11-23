package hcmute.projectBackend2024.service.chat;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import hcmute.projectBackend2024.constant.ResourceName;
import hcmute.projectBackend2024.constant.SearchFields;
import hcmute.projectBackend2024.dto.ListResponse;
import hcmute.projectBackend2024.dto.chat.MessageRequest;
import hcmute.projectBackend2024.dto.chat.MessageResponse;
import hcmute.projectBackend2024.entity.chat.Message;
import hcmute.projectBackend2024.mapper.chat.MessageMapper;
import hcmute.projectBackend2024.repository.authentication.UserRepository;
import hcmute.projectBackend2024.repository.chat.MessageRepository;
import hcmute.projectBackend2024.repository.chat.RoomRepository;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;
    private RoomRepository roomRepository;
    private UserRepository userRepository;
    private MessageMapper messageMapper;

    @Override
    public ListResponse<MessageResponse> findAll(int page, int size, String sort, String filter, String search, boolean all) {
        return defaultFindAll(page, size, sort, filter, search, all, SearchFields.MESSAGE, messageRepository, messageMapper);
    }

    @Override
    public MessageResponse findById(Long id) {
        return defaultFindById(id, messageRepository, messageMapper, ResourceName.MESSAGE);
    }

    @Override
    public MessageResponse save(MessageRequest request) {
        Message message = messageMapper.requestToEntity(request);

        userRepository.findById(request.getUserId()).ifPresent(message::setUser);

        // (1) Save message
        Message messageAfterSave = messageRepository.save(message);

        // (2) Save room
        roomRepository.findById(request.getRoomId())
                .ifPresent(room -> {
                    room.setUpdatedAt(Instant.now());
                    room.setLastMessage(messageAfterSave);
                    roomRepository.save(room);
                });

        return messageMapper.entityToResponse(messageAfterSave);
    }

    @Override
    public MessageResponse save(Long id, MessageRequest request) {
        return defaultSave(id, request, messageRepository, messageMapper, ResourceName.MESSAGE);
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public void delete(List<Long> ids) {
        messageRepository.deleteAllById(ids);
    }

}
