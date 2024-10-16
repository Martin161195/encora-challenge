package encora.notification.repository.impl;

import encora.notification.models.requests.NotificationRequest;
import encora.notification.models.responses.NotificationResponseDTO;
import encora.notification.repository.impl.ChallengeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Function;

@ExtendWith(MockitoExtension.class)
class ChallengeRepositoryTest {

    @Mock
    private WebClient webClient;

    @InjectMocks
    private ChallengeRepository challengeRepository;

    @Test
    void sendNotification() {

        var requestMock = Mockito.mock(WebClient.RequestBodyUriSpec.class);
        var requestBodyMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
        var responseRetrieve = Mockito.mock(WebClient.ResponseSpec.class);

        var responseEntity = new ResponseEntity<>(notificationResponseMock(), HttpStatus.OK);

        Mockito.when(webClient.post())
                .thenReturn(requestMock);

        Mockito.when(requestMock.uri(Mockito.any(Function.class)))
                .thenReturn(requestMock);

        Mockito.when(requestMock.bodyValue(Mockito.any(Object.class)))
                .thenReturn(requestBodyMock);

        Mockito.when(requestBodyMock.retrieve())
                .thenReturn(responseRetrieve);

        Mockito.when(responseRetrieve.toEntity(NotificationResponseDTO.class))
                .thenReturn(Mono.just(responseEntity));


        var response = challengeRepository.sendNotification(NotificationRequest.builder().build());

        StepVerifier.create(response)
                .assertNext(res->{
                    boolean firstCondition = res.getStatusCode() == HttpStatus.OK;
                    Assertions.assertTrue(firstCondition);
                })
                .expectComplete();


    }

    private NotificationResponseDTO notificationResponseMock(){
        return NotificationResponseDTO
                .builder()
                .id("1234")
                .message("this is a test message")
                .build();
    }
}