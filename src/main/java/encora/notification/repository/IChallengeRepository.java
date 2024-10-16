package encora.notification.repository;

import encora.notification.models.requests.NotificationRequest;
import encora.notification.models.responses.NotificationResponseDTO;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface IChallengeRepository {

    Mono<ResponseEntity<NotificationResponseDTO>> sendNotification(NotificationRequest notificationRequestDTO);
}
