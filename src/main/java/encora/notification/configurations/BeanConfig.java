package encora.notification.configurations;

import encora.notification.utils.AppUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import encora.notification.repository.IChallengeRepository;
import encora.notification.repository.impl.ChallengeRepository;
import encora.notification.repository.mocks.IChallengeRepositoryMock;

@Configuration
public class BeanConfig {

    private final String mock = "mock";

    @Bean("webClient")
    public WebClient webClient(){

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector())
                .build();

    }

    @Bean
    public IChallengeRepository iChallengeRepository(){

       return mock=="mock" ? new IChallengeRepositoryMock(appUtils()) : new ChallengeRepository(webClient());

    }

    @Bean
    public AppUtils appUtils(){
        return  new AppUtils();
    }
}
