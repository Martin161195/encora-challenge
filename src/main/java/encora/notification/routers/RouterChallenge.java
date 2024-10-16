package encora.notification.routers;

import encora.notification.handlers.ChallengeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;

@Configuration
public class RouterChallenge {

    private final ChallengeHandler challengeHandler;


    public RouterChallenge(ChallengeHandler challengeHandler) {
        this.challengeHandler = challengeHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routeChallenge(){
        return RouterFunctions.nest(
                path("") ,
                RouterFunctions.route()
                        .POST("/notifications", this.challengeHandler::sendNotifications)
                        .build());
    }

}
