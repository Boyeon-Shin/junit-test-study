package ch06.v6;

import ch06.v1.HealthResult;
import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface NetworkAdapter {
    CompletableFuture<HealthResult> fetchUrlText(String url);
}
