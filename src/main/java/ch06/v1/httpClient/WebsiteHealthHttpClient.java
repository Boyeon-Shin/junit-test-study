package ch06.v1.httpClient;

import ch06.v1.HealthResult;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class WebsiteHealthHttpClient {
    private final HttpClient client;
    private final String url;

    public WebsiteHealthHttpClient(final HttpClient httpClient, final String url) {
        this.client = httpClient;
        this.url = url;
    }

    // 콜백형
    public void isAliveWithCallback(Consumer<HealthResult> callback) {
        var req = HttpRequest.newBuilder(URI.create(url)).GET().build();
        client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
                .thenApply(resp -> {
                    if (resp.statusCode() / 100 != 2) {
                        return new HealthResult(false, "HTTP" + resp.statusCode());
                    }
                    var body = resp.body() == null ? "" : resp.body();
                    return body.contains("illuminated")
                            ? new HealthResult(true, "ok")
                            : new HealthResult(false, "text missing");
                })
                .exceptionally(ex -> new HealthResult(false, ex.getMessage()))
                .thenAccept(callback);
    }

    // async/await 유사: CF 반환
    public CompletableFuture<HealthResult> isAliveAsync() {
        var req = HttpRequest.newBuilder(URI.create(url)).GET().build();
        return client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
                .thenApply(resq -> {
                    if (resq.statusCode() / 100 != 2) {
                        return new HealthResult(false, "HTTP" + resq.statusCode());
                    }
                    var body = resq.body() == null ? "" : resq.body();
                    return body.contains("illuminated")
                            ? new HealthResult(true, "ok")
                            : new HealthResult(false, "text missing");
                })
                .exceptionally(ex -> new HealthResult(false, ex.getMessage()));
    }

}
