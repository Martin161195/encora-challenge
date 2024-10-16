package encora.notification.repository.impl;

import encora.notification.models.requests.NotificationRequest;
import encora.notification.models.responses.NotificationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import encora.notification.repository.IChallengeRepository;

import java.net.URI;

public class ChallengeRepository implements IChallengeRepository {

    private final WebClient webClient;

    @Autowired
    public ChallengeRepository(@Qualifier("webClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<ResponseEntity<NotificationResponseDTO>> sendNotification(NotificationRequest notificationRequest) {

        return webClient
                .post()
                .uri(uriBuilder -> URI.create("/notifications"))
                .bodyValue(notificationRequest)
                .retrieve()
                .toEntity(NotificationResponseDTO.class);

    }
}
