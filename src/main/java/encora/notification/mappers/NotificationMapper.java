package encora.notification.mappers;

import encora.notification.models.requests.NotificationRequest;
import encora.notification.models.requests.NotificationRequestDTO;

public class NotificationMapper {

    public static NotificationRequest notificationBaseMapper(NotificationRequestDTO notificationRequestDTO){
        return NotificationRequest
                .builder()
                .userId(notificationRequestDTO.getUserId())
                .message(notificationRequestDTO.getMessage())
                .build();
    }

}
