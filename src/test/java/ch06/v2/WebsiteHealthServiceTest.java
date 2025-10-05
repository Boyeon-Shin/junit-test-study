package ch06.v2;

import static org.junit.jupiter.api.Assertions.*;

import ch06.v1.HealthResult;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;

class WebsiteHealthServiceTest {

//    @Test
//    void test() throws InterruptedException {
//        WebsiteHealthService service = new WebsiteHealthService();
//
//        CountDownLatch latch = new CountDownLatch(1);
//
//        service.isWebsiteAlive(result -> {
//                    try {
//                        assertThat(result.success()).isFalse();
//                        assertThat(result.status()).isEqualTo("ok");
//                    } finally {
//                        latch.countDown();
//                    }
//                });
//
//        boolean completed = latch.await(10, TimeUnit.SECONDS);
//        assertThat(completed).isTrue();
//    }


    @Test
    void contentMatches_returnsTrue() {
        WebsiteHealthService service = new WebsiteHealthService();

         service.processFetchSuccess("illustrative", (result) -> {
             assertTrue(result.success());
             assertEquals("ok", result.status());
        });
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

    @Test
    void whenFetchFails_returnsFalse2() {
        WebsiteHealthService service = new WebsiteHealthService();

        service.processFetchError(new RuntimeException("error text"), result -> {
            assertFalse(result.success());
            assertEquals("error text", result.status());
        });
    }
}