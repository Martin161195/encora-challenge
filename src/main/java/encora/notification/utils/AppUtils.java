package encora.notification.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class AppUtils {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T1, T2> T1 getObjectFromJsonFile(String fileName, Class<T1> class1, Class<T2> class2) {
        String jsonText = getResourceFileAsString(fileName, class2);
        return this.convertObjectToClass(jsonText, class1);
    }

    private static <T> String getResourceFileAsString(String fileName, Class<T> classz) {
        InputStream is = getResourceFileAsInputStream(fileName, classz);
        if (is != null) {
            // Leer el archivo y unir todas las líneas en una única cadena de texto
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return (String) reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } else {
            throw new RuntimeException("resource not found");
        }
    }

    private static <T> InputStream getResourceFileAsInputStream(String fileName, Class<T> classz) {
        // Usar el ClassLoader de la clase pasada como parámetro para cargar el archivo
        ClassLoader classLoader = classz.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }


    private <T> T convertObjectToClass(final Object body, final Class<T> classz) {
        try {
            // Si el objeto es un String, se interpreta como JSON y se deserializa
            if (body instanceof String) {
                return this.objectMapper.readValue((String) body, classz);
            } else {
                // Si es un array de bytes, también se deserializa; de lo contrario, se convierte directamente
                return body instanceof byte[] ? this.objectMapper.readValue((byte[]) body, classz)
                        : this.objectMapper.convertValue(body, classz);
            }
        } catch (IOException var4) {
            var4.printStackTrace();
            throw new RuntimeException("Error when casting error response");
        }
    }
}
