package encora.notification.services.impl;

import encora.notification.models.requests.NotificationRequest;
import encora.notification.models.requests.NotificationRequestDTO;
import encora.notification.models.responses.NotificationResponseDTO;
import encora.notification.repository.IChallengeRepository;
import encora.notification.repository.impl.ChallengeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChallengeServiceTest {

    @Mock
    private IChallengeRepository challengeRepository; // Mock del repositorio

    @InjectMocks
    private ChallengeService challengeService; // Clase bajo prueba


    @Test
    public void testSendNotification_WithResponseBody() {
        // Datos de prueba
        NotificationRequestDTO requestDTO = new NotificationRequestDTO();
        NotificationRequest notificationBase = new NotificationRequest(UUID.randomUUID(), "message-mock");
        NotificationResponseDTO notificationResponseDTO = new NotificationResponseDTO("1234", "message-mock");
        ResponseEntity<NotificationResponseDTO> responseEntity = new ResponseEntity<>(notificationResponseDTO, HttpStatus.OK);

        // Simulación del comportamiento del repositorio
        when(challengeRepository.sendNotification(any(NotificationRequest.class)))
                .thenReturn(Mono.just(responseEntity));

        // Llamar al método bajo prueba
        Mono<ServerResponse> responseMono = challengeService.sendNotification(requestDTO);

        // Verificar el comportamiento y la respuesta con StepVerifier
        StepVerifier.create(responseMono)
                .expectNextMatches(serverResponse -> serverResponse.statusCode().equals(HttpStatus.OK))
                .verifyComplete();
    }

    /**
     * Test cuando el servicio devuelve un OK sin un cuerpo en la respuesta.
     */
    @Test
    public void testSendNotification_WithoutResponseBody() {
        // Datos de prueba
        NotificationRequestDTO requestDTO = NotificationRequestDTO.builder()
                .id("1234")
                .message("message-mock")
                .build();

        ResponseEntity<NotificationResponseDTO> responseEntity = new ResponseEntity<>(null, HttpStatus.OK);

        // Simulación del comportamiento del repositorio
        when(challengeRepository.sendNotification(any(NotificationRequest.class)))
                .thenReturn(Mono.just(responseEntity));

        // Llamar al método bajo prueba
        Mono<ServerResponse> responseMono = challengeService.sendNotification(requestDTO);

        // Verificar el comportamiento y la respuesta con StepVerifier
        StepVerifier.create(responseMono)
                .expectNextMatches(serverResponse -> serverResponse.statusCode().equals(HttpStatus.OK))
                .verifyComplete();
    }

}