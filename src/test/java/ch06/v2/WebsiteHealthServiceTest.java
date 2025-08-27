package ch06.v2;

import static org.junit.jupiter.api.Assertions.*;

import ch06.v1.HealthResult;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

class WebsiteHealthServiceTest {

    WebsiteHealthService sut = new WebsiteHealthService(null); // RestTemplate 필요 없음

    @Test
     void contentMatchesReturnsTrue() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        final HealthResult[] box = new HealthResult[1];

        sut.processFetchSuccess("illustrative", r -> {
            box[0] = r;
            latch.countDown();
        });

        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertTrue(box[0].success());
        assertEquals("ok", box[0].status());
    }

    @Test
    void websiteContentDoesNotMatchReturnsFalse() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        final HealthResult[] box = new HealthResult[1];

        sut.processFetchSuccess("bad content", r -> {
            box[0] = r;
            latch.countDown();
        });

        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertFalse(box[0].success());
        assertEquals("missing text", box[0].status());
    }

    @Test
    void whenFetchFailsReturnsFalse() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        final HealthResult[] box = new HealthResult[1];

        sut.processFetchError(new Exception("error text"), r -> {
            box[0] = r;
            latch.countDown();
        });

        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertFalse(box[0].success());
        assertEquals("error text", box[0].status());
    }

}