package ch06.v4;

import ch06.v1.HealthResult;
import java.util.concurrent.CompletableFuture;

public class WebsiteHealthService {

    private final NetworkAdapter network;

    public WebsiteHealthService(NetworkAdapter network) {
        this.network = network;
    }

    /**
     * ------------------- 진입점 1 -------------------
     */
    public CompletableFuture<HealthResult> isWebsiteAlive() {
        return network.fetchUrlText("http://example.com")
                .thenApply(result -> {
                    if (!result.success()) {
                        throw new RuntimeException(result.status());
                    }
                    return processFetchSuccess(result.status());
                })
                .exceptionally(err -> processFetchFail(err.getMessage()));
    }

    /**
     * ------------------- 진입점 2 ------------------- 성공 처리
     */
    private HealthResult processFetchSuccess(String text) {
        if (text != null && text.contains("illustrative")) {
            return new HealthResult(true, "ok");
        }
        return new HealthResult(false, "missing text");
    }

    /**
     * ------------------- 진입점 3 ------------------- 실패 처리
     */
    private HealthResult processFetchFail(String err) {
        return new HealthResult(false, err);
    }

}