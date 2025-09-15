package ch06.v4;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import ch06.v1.HealthResult;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WebsiteHealthServiceTest {

    @Mock
    private NetworkAdapter network;

    @InjectMocks
    private WebsiteHealthService verifier;  // @Mock 된 필드를 주입받음

    @Test
    void withGoodContentReturnsTrue() {
        // given: mock fetchUrlText → "illustrative"
        when(network.fetchUrlText("http://example.com"))
                .thenReturn(CompletableFuture.completedFuture(
                        new HealthResult(true, "illustrative")
                ));

        // when
        HealthResult result = verifier.isWebsiteAlive().join();

        // then
        assertTrue(result.success());
        assertEquals("ok", result.status());
    }

    @Test
    void withBadContentReturnsFalse() {
        // given: mock fetchUrlText → 다른 텍스트
        when(network.fetchUrlText("http://example.com"))
                .thenReturn(CompletableFuture.completedFuture(
                        new HealthResult(true, "<span>hello world</span>")
                ));

        // when
        HealthResult result = verifier.isWebsiteAlive().join();

        // then
        assertFalse(result.success());
        assertEquals("missing text", result.status());
    }
}