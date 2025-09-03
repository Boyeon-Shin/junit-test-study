package ch06.v3;

import ch06.v1.HealthResult;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class WebsiteHealthService {

    private final HttpClient client;
    private final String url = "http://example.com";

    public WebsiteHealthService() {
        this.client = HttpClient.newHttpClient();
    }

    /** ------------------- 진입점 1 -------------------
     *  외부 호출 (비동기, async/await 대응)
     */
    public CompletableFuture<HealthResult> isWebsiteAlive() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(this::throwIfResponseNotOK)        // 응답 검증
                .thenApply(HttpResponse::body)                // body 추출
                .thenApply(this::processFetchContent)         // 성공 처리
                .exceptionally(this::processFetchError);      // 오류 처리
    }


    private HttpResponse<String> throwIfResponseNotOK(HttpResponse<String> resp) {
        if (resp.statusCode() / 100 != 2) {
            throw new RuntimeException("HTTP " + resp.statusCode());
        }
        return resp;
    }

    /** ------------------- 진입점 3 -------------------
     *  성공 로직
     */
    public HealthResult processFetchContent(String text) {
        if (text != null && text.contains("illustrative")) {
            return new HealthResult(true, "ok");
        }
        return new HealthResult(false, "missing text");
    }

    /** ------------------- 진입점 4 -------------------
     *  오류 처리 (예외 -> HealthResult 변환)
     */
    public HealthResult processFetchError(Throwable err) {
        return new HealthResult(false, err.getMessage());
    }

}