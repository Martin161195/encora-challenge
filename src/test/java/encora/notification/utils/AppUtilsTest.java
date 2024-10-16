package encora.notification.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import encora.notification.models.responses.NotificationResponseDTO;
import encora.notification.repository.mocks.IChallengeRepositoryMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppUtilsTest {


    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private AppUtils appUtils;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    /**
     * Test para validar la correcta conversión de JSON a un objeto de la clase.
     */
    @Test
    public void testGetObjectFromJsonFile() throws Exception {
        // Datos de prueba
        String fileName = "test-file.json";
        String jsonString = "{\"id\": \"1234\", \"message\": \"message-mock\"}";
        Class<NotificationResponseDTO> class1 = NotificationResponseDTO.class;
        Class<IChallengeRepositoryMock> class2 = IChallengeRepositoryMock.class;

        // Mock del comportamiento de objectMapper.readValue
        NotificationResponseDTO expectedObject = new NotificationResponseDTO("1234", "message-mock");

        // Ejecución del método bajo prueba
        NotificationResponseDTO result = appUtils.getObjectFromJsonFile(fileName, class1, class2);

        // Verificaciones
        assertNotNull(result);
        assertEquals("12345", result.getId());

    }

    /**
     * Test para validar el comportamiento cuando el archivo de recursos no existe.
     */
    @Test
    public void testGetResourceFileAsInputStream_FileNotFound() {
        // Datos de prueba
        String fileName = "non-existent-file.json";
        Class<NotificationResponseDTO> class1 = NotificationResponseDTO.class;
        Class<IChallengeRepositoryMock> class2 = IChallengeRepositoryMock.class;
        // Ejecuta el método y espera que lance una excepción
        Exception exception = assertThrows(RuntimeException.class, () -> {
            appUtils.getObjectFromJsonFile(fileName, class1, class2);
        });

        // Verifica que el mensaje de error sea correcto
        assertEquals("resource not found", exception.getMessage());
    }





}