package ch06.timer.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void test() {
        TimeProvider timeProvider = new TestTImeProvider();
        Calculator calculator = new Calculator(timeProvider);

        calculator.calculator1(1, 2, result -> {
            Assertions.assertEquals(3, result);
        });

    }

}