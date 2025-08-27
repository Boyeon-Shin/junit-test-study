package ch06.v3;

import ch06.v1.HealthResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

public class WebsiteHealthService {
    private final RestTemplate restTemplate;
    private final String url = "http://example.com";

    public WebsiteHealthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /** ------------------- 진입점 1 -------------------
     * async/await 스타일 (CompletableFuture 반환)
     */
    public CompletableFuture<HealthResult> isWebsiteAlive() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                ResponseEntity<String> resp = restTemplate.getForEntity(url, String.class);
                throwIfResponseNotOK(resp);
                String body = resp.getBody() == null ? "" : resp.getBody();
                return processFetchContent(body);
            } catch (Exception e) {
                return processFetchError(e);
            }
        });
    }

    private void throwIfResponseNotOK(ResponseEntity<String> resp) {
        if (!resp.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException(resp.getStatusCode().toString());
        }
    }

    /** ------------------- 진입점 2 -------------------
     * 성공 로직: 본문에 "illustrative" 포함 여부 검사
     * 콜백 함수 대신 값 반환
     */
    public HealthResult processFetchContent(String text) {
        String safe = (text == null) ? "" : text;
        if (safe.contains("illustrative")) {
            return new HealthResult(true, "ok");
        }
        return new HealthResult(false, "missing text");
    }

    /** ------------------- 진입점 3 -------------------
     * 오류 로직: 예외 래핑
     * 값을 반환하는 개신 오류 발생
     */
    public HealthResult processFetchError(Exception err) {
        String msg = (err == null) ? "unknown error" : err.getMessage();
        throw new RuntimeException(msg);
    }
}