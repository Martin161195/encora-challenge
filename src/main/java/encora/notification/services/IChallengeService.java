package encora.notification.services;

import encora.notification.models.requests.NotificationRequestDTO;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface IChallengeService {

    Mono<ServerResponse> sendNotification(NotificationRequestDTO notificationRequestDTO);
}
