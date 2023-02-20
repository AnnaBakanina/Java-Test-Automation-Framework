package Java11HttpClient;

import org.junit.jupiter.api.Assertions;
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
                .GET() // GET is used by default if Request type is not provided
                .setHeader("User-Agent", "Java 11 Learning")
                .build();

        HttpResponse<Void> response = httpClient.send(get, HttpResponse.BodyHandlers.discarding());
        int actualCode = response.statusCode();

        Assertions.assertEquals(200, actualCode);
    }

    @Test
    void contentTypeIsJSON() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL))
                .setHeader("User-Agent", "Java 11 Learning")
                .build();

        HttpResponse<Void> response = httpClient.send(get, HttpResponse.BodyHandlers.discarding());
        String contentType = response.headers().firstValue("content-type").get();

        Assertions.assertEquals("application/json; charset=utf-8", contentType);
    }

    @Test
    void xRateLimitIsPresent() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL))
                .setHeader("User-Agent", "Java 11 Learning")
                .build();

        HttpResponse<Void> response = httpClient.send(get, HttpResponse.BodyHandlers.discarding());
        String xrateLimit = response.headers().firstValue("x-Ratelimit-Limit").get();

        Assertions.assertEquals(60, Integer.parseInt(xrateLimit));
    }
}
