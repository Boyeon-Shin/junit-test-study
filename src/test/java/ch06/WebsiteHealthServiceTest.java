package ch06;

import static org.junit.jupiter.api.Assertions.*;

import ch06.v1.HealthResult;
import ch06.v1.restTemplate.WebsiteHealthService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

class WebsiteHealthServiceTest {

    @Test
    void networkRequiredCallbackCorrectContentTrue() throws Exception {
        var restTemplate = new RestTemplate();
        var sut = new WebsiteHealthService(restTemplate);

        CountDownLatch latch = new CountDownLatch(1);
        final HealthResult[] box = new HealthResult[1];

        sut.isWebsiteAliveWithCallback(result -> {
            box[0] = result;
            latch.countDown();
        });

        assertTrue(latch.await(10, TimeUnit.SECONDS), "콜백이 제떄 호촐되지 않았습니다.");

        assertNotNull(box[0], "결과가 null입니다");
        assertTrue(box[0].success());
        assertEquals("ok", box[0].status());
    }

    @Test
    void networkRequiredAwaitCorrectContentTrue () throws Exception {
        var restTemplate = new RestTemplate();
        var sut = new WebsiteHealthService(restTemplate);

        // 완료를 최대 10초까지 기다린 뒤 결과 검증
        HealthResult result = sut.isWebsiteAliveWithAsyncAwait().get(10, TimeUnit.SECONDS);

        assertTrue(result.success());
        assertEquals("ok", result.status());
    }
}