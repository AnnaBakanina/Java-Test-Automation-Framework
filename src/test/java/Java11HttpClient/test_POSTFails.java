package Java11HttpClient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test_POSTFails {
    private static final String BASE_URL = "https://api.github.com/";

    @Test
    void postWithoutAuthorizationFails() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest post = HttpRequest.newBuilder(URI.create(BASE_URL + ""))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<Void> response = httpClient.send(post, HttpResponse.BodyHandlers.discarding());
        int actualCode = response.statusCode();

        assertEquals(401, actualCode);
    }
}
