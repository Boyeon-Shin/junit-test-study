package ch06.v6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch06.v1.HealthResult;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

class WebsiteVerifierInterfaceInjectionTest {

    private NetworkAdapter makeStubNetworkWithResult(HealthResult fakeResult) {
        return url -> CompletableFuture.completedFuture(fakeResult);
    }


    @Test
    void withGoodContent_returnsTrue() throws Exception {
        NetworkAdapter stubSyncNetwork = makeStubNetworkWithResult(
                new HealthResult(true, "illustrative"));

        WebsiteVerifierFunctionalInjection verifier = new WebsiteVerifierFunctionalInjection();
        HealthResult result = verifier.isWebSiteAlive(stubSyncNetwork).get(1, TimeUnit.SECONDS);

        assertTrue(result.success());
        assertEquals("ok" , result.status());
    }


    @Test
    void withBadContent_returnsFalse() throws Exception {
        NetworkAdapter stubAsyncNetwork = makeStubNetworkWithResult(
                new HealthResult(true, "unexcepted content"));

        WebsiteVerifierFunctionalInjection verifier = new WebsiteVerifierFunctionalInjection();
        HealthResult result = verifier.isWebSiteAlive(stubAsyncNetwork).get(1, TimeUnit.SECONDS);


        assertFalse(result.success());
        assertEquals("missing text" , result.status());
    }


    @Test
    void withFetchErrors_returnFalse_onMessage() throws Exception {
        NetworkAdapter stubAsyncNetwork = makeStubNetworkWithResult(
                new HealthResult(false, "network error"));

        WebsiteVerifierFunctionalInjection verifier = new WebsiteVerifierFunctionalInjection();
        HealthResult result = verifier.isWebSiteAlive(stubAsyncNetwork).get(1, TimeUnit.SECONDS);

        assertFalse(result.success());
        assertEquals("network error" , result.status());
    }
}