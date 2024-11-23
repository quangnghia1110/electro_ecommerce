package hcmute.projectBackend2024.service.general;

import hcmute.projectBackend2024.dto.general.NotificationResponse;

public interface NotificationService {

    void pushNotification(String uniqueKey, NotificationResponse notification);

}
