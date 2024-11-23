package hcmute.projectBackend2024.service.chat;

import hcmute.projectBackend2024.dto.chat.MessageRequest;
import hcmute.projectBackend2024.dto.chat.MessageResponse;
import hcmute.projectBackend2024.generic.CrudService;

public interface MessageService extends CrudService<Long, MessageRequest, MessageResponse> {}
