package ch06.v2;

import ch06.v1.HealthResult;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Consumer;

public class WebsiteHealthService {

    private final HttpClient client;
    private final String url = "http://example.com";

    public WebsiteHealthService() {
        this.client = HttpClient.newHttpClient(); // 기본 HttpClient
    }

    /** ------------------- 진입점 1 -------------------
     *  외부 호출 + 콜백 (비동기)
     */
    public void isWebsiteAlive(Consumer<HealthResult> callback) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(this::throwOnInvalidResponse)  // 응답 검증
                .thenApply(HttpResponse::body)            // body 추출
                .thenAccept(body -> processFetchSuccess(body, callback)) // 성공 처리
                .exceptionally(err -> { // 오류 처리
                    processFetchError(err, callback);
                    return null;
                });
    }

    private HttpResponse<String> throwOnInvalidResponse(HttpResponse<String> resp) {
        if (resp.statusCode() / 100 != 2) {
            throw new RuntimeException("HTTP " + resp.statusCode());
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
    public void processFetchError(Throwable err, Consumer<HealthResult> callback) {
        callback.accept(new HealthResult(false, err.getMessage()));
    }
}