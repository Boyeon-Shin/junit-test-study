package ch02.V1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch02.v0.Rule;
import org.assertj.core.api.Assertions;


import ch02.v0.RuleResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class OneUpperCaseRuleTest {
    OneUpperCaseRule oneUpperCaseRule;

    @BeforeEach
    void setup(){
        oneUpperCaseRule = new OneUpperCaseRule();
    }

    @Test
    @DisplayName("give no uppercase, it fails")
    void test1 () {
        RuleResult result = oneUpperCaseRule.check("abc");
        Assertions.assertThat(result.isPassed()).isEqualTo(false);
    }


    @Test
    @DisplayName("give one uppercase, it passes")
    void test2 () {
        RuleResult result = oneUpperCaseRule.check("Abc");
        Assertions.assertThat(result.isPassed()).isEqualTo(true);
    }


    @ParameterizedTest
    @ValueSource(strings = {"Abc", "aBc"})
    void test3 (String input) {
        RuleResult result = oneUpperCaseRule.check(input);
        Assertions.assertThat(result.isPassed()).isEqualTo(true);
//        assertTrue(result.isPassed());
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "123", "!"})
    void test4 (String input) {
        RuleResult result = oneUpperCaseRule.check(input);
        assertFalse(result.isPassed());
        assertEquals("at least one upper case needed", result.getReason());
    }

    @ParameterizedTest
    @CsvSource({
            "Abc, true",
            "aBc, true",
            "abc, false"
    })
    @DisplayName("대문자 포함 여부에 따라 결과를 다르게 반환해야 함")
    void testWithMultipleInputs(String input, boolean expected) {
        RuleResult result =  oneUpperCaseRule.check(input);
        assertEquals(expected, result.isPassed());
    }
}