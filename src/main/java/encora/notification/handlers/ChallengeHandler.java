package encora.notification.handlers;

import encora.notification.models.requests.NotificationRequestDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import encora.notification.services.IChallengeService;

@Component
public class ChallengeHandler {

    private final IChallengeService challengeService;

    public ChallengeHandler(IChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    public Mono<ServerResponse> sendNotifications(ServerRequest serverRequest){

        var body  = serverRequest.bodyToMono(NotificationRequestDTO.class);

        return body.
                flatMap(challengeService::sendNotification)
                .doOnError(e->{
                    System.out.println("there was an error");
                });

    }


}
