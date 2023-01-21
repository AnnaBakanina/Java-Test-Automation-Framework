package Java11HttpClient;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Java11HttpClient {

    private static final String BASE_URL = "https://api.github.com/";

    @Test
    void getReturns200() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL))
                .GET()
                .setHeader("User-Agent", "Java 11 Learning")
                .build();

        HttpResponse<Void> response = httpClient.send(get, HttpResponse.BodyHandlers.discarding());
    }
}
