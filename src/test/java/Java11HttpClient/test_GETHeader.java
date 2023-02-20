package Java11HttpClient;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

public class test_GETHeader {

    private static final String BASE_URL = "https://api.github.com/";

    static HttpClient httpClient = HttpClient.newBuilder().build();
    static HttpResponse<Void> response;

    @BeforeAll
    static void sendGetToBaseEndpoint() throws IOException, InterruptedException {
        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL))
                .setHeader("User-Agent", "Java 11 Learning")
                .build();

        response = httpClient.send(get, HttpResponse.BodyHandlers.discarding());
    }

    @Test
    void getReturns200() throws IOException, InterruptedException {
        int actualCode = response.statusCode();
        assertEquals(200, actualCode);
    }

    @ParameterizedTest
    @CsvSource({
            "x-Ratelimit-Limit, 60",
            "content-type, application/json; charset=utf-8",
            "server, GitHub.com",
            "x-frame-options, deny"
    })
    void parametrizedTestForHeaders(String header, String expectedValue) throws IOException, InterruptedException {
        String contentType = response.headers().firstValue(header).get();
        assertEquals(expectedValue, contentType);
    }
}