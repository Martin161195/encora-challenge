package encora.notification.repository.mocks;

import encora.notification.models.requests.NotificationRequest;
import encora.notification.models.responses.NotificationResponseDTO;
import encora.notification.utils.AppUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import encora.notification.repository.IChallengeRepository;

public class IChallengeRepositoryMock implements IChallengeRepository {

    private final AppUtils appUtils;

    public IChallengeRepositoryMock(AppUtils appUtils) {
        this.appUtils = appUtils;
    }

    @Override
    public Mono<ResponseEntity<NotificationResponseDTO>> sendNotification(NotificationRequest notificationRequestDTO) {

        NotificationResponseDTO responseMock = null;
        try {
            responseMock = this.appUtils.getObjectFromJsonFile("mocks/notification-mock.json", NotificationResponseDTO.class, IChallengeRepositoryMock.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        var responseEntity = ResponseEntity.ok().body(responseMock);
        return Mono.just(responseEntity);
    }
}
