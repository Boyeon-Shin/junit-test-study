package ch05.stub;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PasswordVerifier3Test {

    @Mock
    IComplicatedLogger logger;
    @Mock
    MaintenanceWindow maintenance;

    @Test
    void maintenanceModeBlocksVerificationAndLogsUnderMaintenance() {
        // given
        given(maintenance.isUnderMaintenance()).willReturn(true);
        PasswordVerifier3 sut = new PasswordVerifier3(List.of(), logger, maintenance);

        // when
        boolean result = sut.verify("anything");

        // then
        assertFalse(result);
        verify(logger).info(eq("Under Maintenance"), eq("verify"));
        verifyNoMoreInteractions(logger);
    }

    @Test
    void passesWhenAllRulesPassAndLogsPassed() {
        // given: not under maintenance, 두 규칙 모두 통과
        given(maintenance.isUnderMaintenance()).willReturn(false);
        Predicate<String> pass1 = s -> true;
        Predicate<String> pass2 = s -> s.length() >= 0;
        PasswordVerifier3 sut = new PasswordVerifier3(List.of(pass1, pass2), logger, maintenance);

        // when
        boolean result = sut.verify("abc");

        // then
        assertTrue(result);
        verify(logger).info(eq("PASSED"), eq("verify"));
    }

    @Test
    void failsWhenAnyRuleFailsAndLogsFail() {
        // given: not under maintenance, 하나라도 실패
        given(maintenance.isUnderMaintenance()).willReturn(false);
        Predicate<String> pass = s -> true;
        Predicate<String> fail = s -> false;
        PasswordVerifier3 sut = new PasswordVerifier3(List.of(pass, fail), logger, maintenance);

        // when
        boolean result = sut.verify("abc");

        // then
        assertFalse(result);
        verify(logger).info(eq("FAIL"), eq("verify"));
    }
}