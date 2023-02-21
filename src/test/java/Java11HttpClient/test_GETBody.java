package Java11HttpClient;

import com.testframework.entities.User;
import com.testframework.handlers.JsonBodyHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test_GETBody {
    private static final String BASE_URL = "https://api.github.com/";

    @Test
    void bodyContainsCurrentUrl() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL + "users/AnnaBakanina"))
                .setHeader("User-Agent", "Java 11 Learning")
                .build();

        HttpResponse<User> response = httpClient.send(get, JsonBodyHandler.jsonBodyHandler(User.class));
        String actualLogin = response.body().getLogin();

        assertEquals("AnnaBakanina", actualLogin);
    }
}
