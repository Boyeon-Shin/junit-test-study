package ch06.v5;

import ch06.v1.HealthResult;
import java.util.concurrent.CompletableFuture;

public class WebsiteVerifier {

    private final NetworkAdapter network;

    public WebsiteVerifier(NetworkAdapter network) {
        this.network = network;
    }

    /**
     * ------------------- 진입점 -------------------
     */
    public CompletableFuture<HealthResult> isWebsiteAlive() {
        return network.fetchUrlText("http://example.com")
                .thenApply(result -> {
                    if (result.success()) {
                        return onFetchSuccess(result.status());
                    } else {
                        return onFetchError(result.status());
                    }
                });
    }

    /**
     * ------------------- 성공 처리 -------------------
     */
    private HealthResult onFetchSuccess(String text) {
        if (text != null && text.contains("illustrative")) {
            return new HealthResult(true, "ok");
        }
        return new HealthResult(false, "missing text");
    }

    /**
     * ------------------- 오류 처리 -------------------
     */
    private HealthResult onFetchError(String err) {
        return new HealthResult(false, err);
    }
}
