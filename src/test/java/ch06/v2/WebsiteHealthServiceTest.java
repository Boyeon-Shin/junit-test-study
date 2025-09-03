package ch06.v2;

import static org.junit.jupiter.api.Assertions.*;

import ch06.v1.HealthResult;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;

class WebsiteHealthServiceTest {

    @Test
    void contentMatches_returnsTrue() throws Exception {
        WebsiteHealthService service = new WebsiteHealthService();

        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<HealthResult> ref = new AtomicReference<>();

        service.processFetchSuccess("illustrative", result -> {
            ref.set(result);
            latch.countDown();
        });

        assertTrue(latch.await(2, TimeUnit.SECONDS), "콜백이 호출되지 않았습니다");

        HealthResult r = ref.get();
        assertNotNull(r);
        assertTrue(r.success());
        assertEquals("ok", r.status());
    }

    @Test
    void contentNotMatch_returnsFalse() throws Exception {
        WebsiteHealthService service = new WebsiteHealthService();

        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<HealthResult> ref = new AtomicReference<>();

        service.processFetchSuccess("bad content", result -> {
            ref.set(result);
            latch.countDown();
        });

        assertTrue(latch.await(2, TimeUnit.SECONDS), "콜백이 호출되지 않았습니다");

        HealthResult r = ref.get();
        assertNotNull(r);
        assertFalse(r.success());
        assertEquals("missing text", r.status());
    }

    @Test
    void whenFetchFails_returnsFalse() throws Exception {
        WebsiteHealthService service = new WebsiteHealthService();

        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<HealthResult> ref = new AtomicReference<>();

        service.processFetchError(new RuntimeException("error text"), result -> {
            ref.set(result);
            latch.countDown();
        });

        assertTrue(latch.await(2, TimeUnit.SECONDS), "콜백이 호출되지 않았습니다");

        HealthResult r = ref.get();
        assertNotNull(r);
        assertFalse(r.success());
        assertEquals("error text", r.status());
    }

}