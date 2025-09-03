package ch06.v5;

import ch06.v1.HealthResult;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class NetworkAdapter {
    private final HttpClient client;

    public NetworkAdapter() {
        this.client = HttpClient.newHttpClient();
    }

    public CompletableFuture<HealthResult> fetchUrlText(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(resp -> {
                    if (resp.statusCode() / 100 == 2) {
                        return new HealthResult(true, resp.body());
                    } else {
                        return new HealthResult(false, resp.statusCode() + " " + resp.body());
                    }
                });
    }

}
