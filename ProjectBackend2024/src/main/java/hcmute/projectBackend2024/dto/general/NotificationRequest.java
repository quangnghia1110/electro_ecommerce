package hcmute.projectBackend2024.dto.general;

import lombok.Data;
import org.springframework.lang.Nullable;

import hcmute.projectBackend2024.entity.general.NotificationType;

@Data
public class NotificationRequest {
    private Long userId;
    private NotificationType type;
    private String message;
    @Nullable
    private String anchor;
    private Integer status;
}
