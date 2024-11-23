package hcmute.projectBackend2024.dto.general;

import lombok.Data;
import org.springframework.lang.Nullable;

import hcmute.projectBackend2024.entity.general.NotificationType;

import java.time.Instant;

@Data
public class NotificationResponse {
    private Long id;
    private Instant createdAt;
    private NotificationType type;
    private String message;
    @Nullable
    private String anchor;
    private Integer status;
}
