package ch05.v1;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PasswordVerifierTest {

    @Mock
    ConfigurationService config;
    @Mock
    ComplicatedLogger logger;

    private PasswordVerifier verifier;

    @BeforeEach
    void setUp() {
        verifier = new PasswordVerifier(config, logger);
    }

    @Test
    void withInfoLevelAndNoRuleCallLoggerWithPASS() {
        given(config.getLogLevel()).willReturn("info");

        // when
        boolean result = verifier.verifyPassword("anything", List.of());

        //then
        assertTrue(result);
        verify(logger).info(matches(".*Passed.*"));
        verify(logger, never()).debug(anyString());
    }

    @Test
    void withDebugLogLevelAndNoRulesCallsLoggerWithPASSED() {
        given(config.getLogLevel()).willReturn("debug");

        // when
        boolean result = verifier.verifyPassword("anything", List.of());

        // then
        assertTrue(result);
        verify(logger).debug(matches(".*Passed.*"));
        verify(logger, never()).info(anyString());
    }
}