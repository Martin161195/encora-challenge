package encora.notification.models.requests;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class NotificationRequest {
    private UUID userId;
    private String message;

   public NotificationRequest(UUID userId, String message){
        this.userId = userId;
        this.message = message;
    }


}
