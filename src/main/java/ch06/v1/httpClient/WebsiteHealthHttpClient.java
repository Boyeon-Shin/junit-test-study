package ch06.v1.httpClient;

import ch06.v1.HealthResult;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class WebsiteHealthHttpClient {

    private static final String url = "http://example.com";
    private final HttpClient client = HttpClient.newHttpClient();

    // 콜백 버전
    public void isWebsiteAliveWithCallback(Consumer<HealthResult> callback) {
        HttpRequest req = HttpRequest.newBuilder(URI.create(url)).GET().build();

        client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
                .thenApply(resp -> {
                    if (resp.statusCode() / 100 != 2) {
                        // 네트워크 문제 / 비정상 응답 시
                        throw new RuntimeException("HTTP " + resp.statusCode());
                    }
                    return resp.body();
                })
                .thenApply(body -> {
                    if (body.contains("illustrative")) {
                        return new HealthResult(true, "ok");
                    } else {
                        // text missing 시나리오
                        return new HealthResult(false, "text missing");
                    }
                })
                .exceptionally(ex -> {
                    // 예외 시 종료점
                    return new HealthResult(false, ex.getMessage());
                })
                .thenAccept(callback);
    }

    // async/await 유사 (CompletableFuture 반환)
    public CompletableFuture<HealthResult> isWebsiteAliveWithAsyncAwait() {
        HttpRequest req = HttpRequest.newBuilder(URI.create(url)).GET().build();

        return client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
                .thenApply(resp -> {
                    if (resp.statusCode() / 100 != 2) {
                        // 비정상 응답 처리
                        throw new RuntimeException("HTTP " + resp.statusCode());
                    }
                    return resp.body();
                })
                .thenApply(body -> {
                    if (body.contains("illustrative")) {
                        return new HealthResult(true, "ok");
                    }
                    throw new RuntimeException("text missing");
                })
                .exceptionally(ex -> {
                    return new HealthResult(false, ex.getMessage());
                });
    }
}