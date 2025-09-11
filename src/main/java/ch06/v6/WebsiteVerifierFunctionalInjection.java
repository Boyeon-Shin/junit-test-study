package ch06.v6;

import ch06.v1.HealthResult;
import java.util.concurrent.CompletableFuture;

public class WebsiteVerifierFunctionalInjection {
    public CompletableFuture<HealthResult> isWebSiteAlive(NetworkAdapter network) {
        return network.fetchUrlText("https://example.com")
                .thenApply(result -> {
                    if (result.success()) {
                        String text = result.status();
                        return onFetchSuccess(text);
                    }
                    return onFetchError(result.status());
                });
    }

    private HealthResult onFetchSuccess(String text) {
        return text.contains("illustrative")
                ? new HealthResult(true, "ok")
                : new HealthResult(false, "missing text");
    }

    private HealthResult onFetchError(String err) {
        return new HealthResult(false, err);
    }
}
