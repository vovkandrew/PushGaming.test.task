package pushgaming.test.task.demo.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class CustomHttpClient {
    private static final int OK_STATUS_CODE = 200;
    private static final String UNEXPECTED_ERROR_MSG =
            "Something gone wrong with the request, please check the Http status code for more info";

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String getResponseBodyFromClient(String path) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(path))
                .build();

        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException();
        }
        if (response.statusCode() == OK_STATUS_CODE ) {
            return response.body();
        } else {
            throw new HttpClientErrorException(HttpStatus.valueOf(response.statusCode()), UNEXPECTED_ERROR_MSG);
        }
    }
}
