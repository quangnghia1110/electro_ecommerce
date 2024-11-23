package hcmute.projectBackend2024.service.general;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import hcmute.projectBackend2024.dto.general.NotificationResponse;
import hcmute.projectBackend2024.mapper.general.EventMapper;
import hcmute.projectBackend2024.repository.general.EmitterRepository;

import java.io.IOException;

@Service
@Primary
@AllArgsConstructor
@Slf4j
public class SseNotificationService implements NotificationService {

    private final EmitterRepository emitterRepository;
    private final EventMapper eventMapper;

    // uniqueKey thường là username
    @Override
    public void pushNotification(String uniqueKey, NotificationResponse notification) {
        emitterRepository
                .getByUniqueKey(uniqueKey)
                .ifPresentOrElse(emitter -> {
                    try {
                        log.debug("Sending event: {} for key: {}", notification, uniqueKey);
                        // Reference: https://stackoverflow.com/a/42803814
                        emitter.send(eventMapper.toSseEventBuilder("message", notification));
                    } catch (IOException | IllegalStateException e) {
                        log.debug("Error while sending event: {} for key: {} - exception: {}", notification, uniqueKey, e);
                        emitterRepository.remove(uniqueKey);
                    }
                }, () -> log.debug("No emitter for key: {}", uniqueKey));
    }

}
