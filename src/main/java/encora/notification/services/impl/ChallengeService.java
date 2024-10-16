package encora.notification.services.impl;

import encora.notification.mappers.NotificationMapper;
import encora.notification.models.requests.NotificationRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import encora.notification.repository.IChallengeRepository;
import encora.notification.services.IChallengeService;

@Service
public class ChallengeService implements IChallengeService {

    private final IChallengeRepository challengeRepository;

    public ChallengeService(IChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    @Override
    public Mono<ServerResponse> sendNotification(NotificationRequestDTO notificationRequestDTO) {

        var notificationBase = NotificationMapper.notificationBaseMapper(notificationRequestDTO);

        return this.challengeRepository.sendNotification(notificationBase)
                .flatMap(response->{
                    if(response.getStatusCode()==HttpStatus.OK && response.getBody()!=null){
                        var bodyResponse = response.getBody();
                        return ServerResponse
                                .ok()
                                .bodyValue(bodyResponse);

                    }
                    return ServerResponse
                            .ok()
                            .build();

                })
                .onErrorComplete();
    }
}
