package ch06.v1.httpClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WebsiteHealthHttpClientTest {

    private WebsiteHealthHttpClient service;

    @BeforeEach
    public void setup() {
        service = new WebsiteHealthHttpClient();
    }

    @Test
    void callbackVersionShouldReturnOk() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1); //콜백 완료를 기다리는 장치

        service.isWebsiteAliveWithCallback(result -> {
            try {
                assertThat(result.success()).isTrue();
                assertThat(result.status()).isEqualTo("ok");
            } finally {
                latch.countDown();   //테스트 종료 신호
            }
        });

        // 10초 안에 결과 안 오면 실패 처리
        boolean completed = latch.await(10, TimeUnit.SECONDS);
        assertThat(completed).isTrue();
    }

    @Test
    void asyncAwaitVersionShouldReturnOkWithThenAccept() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);

        service.isWebsiteAliveWithAsyncAwait()
                .thenAccept(result -> {
                    try {
                        assertThat(result.success()).isFalse();
                        assertThat(result.status()).isEqualTo("ok");
                    } finally {
                        latch.countDown();
                    }
                });

        boolean completed = latch.await(10, TimeUnit.SECONDS);
        assertThat(completed).isTrue();
    }


    @Test
    void asyncAwaitVersionShouldReturnOkWithJoin() {
        var result = service.isWebsiteAliveWithAsyncAwait().join(); // join은 unchecked 예외 발생
        assertThat(result.success()).isTrue();
        assertThat(result.status()).isEqualTo("ok");
    }
}