package ch06.v2;

import ch06.v1.HealthResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class WebsiteHealthService {
    private final RestTemplate restTemplate;
    private final String url = "http://example.com";

    public WebsiteHealthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /** ------------------- 진입점 1 -------------------
     *  외부 호출 + 콜백
     */
    public void isWebsiteAlive(Consumer<HealthResult> callback) {
        CompletableFuture.runAsync(() -> {
            try {
                ResponseEntity<String> resp = restTemplate.getForEntity(url, String.class);
                throwOnInvalidResponse(resp);
                String body = resp.getBody() == null ? "" : resp.getBody();
                processFetchSuccess(body, callback);
            } catch (Exception e) {
                processFetchError(e, callback);
            }
        });
    }

    private ResponseEntity<String> throwOnInvalidResponse(ResponseEntity<String> resp) {
        if (!resp.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException(resp.getStatusCode().toString());
        }
        return resp;
    }

    /** ------------------- 진입점 2 -------------------
     *  성공 로직만 따로 분리 (순수 함수)
     */
    public void processFetchSuccess(String text, Consumer<HealthResult> callback) {
        if (text.contains("illustrative")) {
            callback.accept(new HealthResult(true, "ok"));
        } else {
            callback.accept(new HealthResult(false, "missing text"));
        }
    }

    /** ------------------- 진입점 3 -------------------
     *  오류 로직만 따로 분리 (순수 함수)
     */
    public void processFetchError(Exception err, Consumer<HealthResult> callback) {
        callback.accept(new HealthResult(false, err.getMessage()));
    }
}