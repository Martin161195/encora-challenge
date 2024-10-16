package encora.notification.models.requests;


import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDTO {
    @Generated
    private UUID userId;
    private String message;
    private String id;
    private LocalDateTime timestamp;

}
