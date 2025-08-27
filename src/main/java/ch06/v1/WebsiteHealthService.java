package ch06.v1;

import java.net.http.HttpClient;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class WebsiteHealthService {
    private final RestTemplate restTemplate;
    private final String url = "http://example.com";
    private final Executor executor = Executors.newCachedThreadPool(); // 콜백용

    public WebsiteHealthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /** 콜백 버전: isWebsiteAliveWithCallback(callback) */
    public void isWebsiteAliveWithCallback(Consumer<HealthResult> callback) {
        CompletableFuture.runAsync(() -> {
            try {
                ResponseEntity<String> resp = restTemplate.getForEntity(url, String.class);
                if (!resp.getStatusCode().is2xxSuccessful()) {
                    callback.accept(new HealthResult(false,
                            resp.getStatusCode().toString()));
                    return;
                }
                String body = resp.getBody() == null ? "" : resp.getBody();
                if (body.contains("illustrative")) {
                    callback.accept(new HealthResult(true, "ok"));
                } else {
                    callback.accept(new HealthResult(false, "text missing"));
                }
            } catch (Exception e) {
                callback.accept(new HealthResult(false, e.getMessage()));
            }
        }, executor);
    }

    /** async/await 유사 버전: CompletableFuture 리턴 */
    public CompletableFuture<HealthResult> isWebsiteAliveWithAsyncAwait() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                ResponseEntity<String> resp = restTemplate.getForEntity(url, String.class);
                if (!resp.getStatusCode().is2xxSuccessful()) {
                    return new HealthResult(false, resp.getStatusCode().toString());
                }
                String body = resp.getBody() == null ? "" : resp.getBody();
                if (body.contains("illustrative")) {
                    return new HealthResult(true, "ok");
                }
                return new HealthResult(false, "text missing");
            } catch (Exception e) {
                return new HealthResult(false, e.getMessage());
            }
        });
    }
}