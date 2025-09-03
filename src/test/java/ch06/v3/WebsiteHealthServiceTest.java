package ch06.v3;

import static org.junit.jupiter.api.Assertions.*;

import ch06.v1.HealthResult;
import org.junit.jupiter.api.Test;

class WebsiteHealthServiceTest {

    @Test
    void onFetchSuccessWithGoodContentReturnsTrue() {
        WebsiteHealthService service = new WebsiteHealthService();

        HealthResult result = service.processFetchContent("illustrative");

        assertTrue(result.success());
        assertEquals("ok", result.status());
    }

    @Test
    void onFetchSuccessWithBadContentReturnsFalse() {
        WebsiteHealthService service = new WebsiteHealthService();

        HealthResult result = service.processFetchContent("text not on site");

        assertFalse(result.success());
        assertEquals("missing text", result.status());
    }

    @Test
    void onFetchFailReturnsHealthResultWithErrorMessage() {
        WebsiteHealthService service = new WebsiteHealthService();
        HealthResult result = service.processFetchError(new RuntimeException("error text"));

        assertFalse(result.success());
        assertEquals("error text", result.status());
    }
}