//package ch06.v1.webClient;
//
//import ch06.v1.HealthResult;
//import java.time.Duration;
//import java.util.function.Consumer;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//
//public class WebsiteHealthWebClient {
//    private final WebClient webClient;
//    private final String url;
//
//    public WebsiteHealthWebClient(WebClient webClient, String url) {
//        this.webClient = webClient;
//        this.url = url;
//    }
//
//    public void isAliveWithCallback(Consumer<HealthResult> callback) {
//        webClient.get().uri(url)
//                .retrieve()
//                .onStatus(HttpStatusCode::isError, resp -> resp.createException())
//                                .bodyToMono(String.class)
//                                .timeout(Duration.ofSeconds(5))
//                .map(body -> body != null && body.contains("illustrative")
//                        ? new HealthResult(true, "ok")
//                        : new HealthResult(false, "text missing"))
//                .onErrorReturn(new HealthResult(false, "error"))
//                .subscribe(callback); // 비동기 콜백
//    }
//
//    /** async/await 유사: Mono 반환 (원하면 .toFuture() 가능) */
//    public Mono<HealthResult> isAliveAsync() {
//        return webClient.get().uri(url)
//                .retrieve()
//                .onStatus(HttpStatusCode::isError, resp -> resp.createException())
//                .bodyToMono(String.class)
//                .map(body -> body != null && body.contains("illustrative")
//                        ? new HealthResult(true, "ok")
//                        : new HealthResult(false, "text missing"))
//                .onErrorResume(ex -> Mono.just(new HealthResult(false, String.valueOf(ex.getMessage()))));
//    }
//}