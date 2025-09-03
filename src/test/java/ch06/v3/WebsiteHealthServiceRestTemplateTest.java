package ch06.v3;

import static org.junit.jupiter.api.Assertions.*;

import ch06.v1.HealthResult;
import org.junit.jupiter.api.Test;

class WebsiteHealthServiceRestTemplateTest {

    WebsiteHealthServiceRestTemplate sut = new WebsiteHealthServiceRestTemplate(null); // RestTemplate 안 씀

    @Test
    void onFetchSuccessWithGoodContentReturnsTrue() {
        HealthResult result = sut.processFetchContent("illustrative");

        assertTrue(result.success());
        assertEquals("ok", result.status());
    }

    @Test
    void onFetchSuccessWithBadContentReturnsFalse() {
        HealthResult result = sut.processFetchContent("text not on site");

        assertFalse(result.success());
        assertEquals("missing text", result.status());
    }

    @Test
    void onFetchFailThrows() {
        Exception ex = assertThrows(RuntimeException.class,
                () -> sut.processFetchError(new RuntimeException("error text")));
        assertEquals("error text", ex.getMessage());
    }

}