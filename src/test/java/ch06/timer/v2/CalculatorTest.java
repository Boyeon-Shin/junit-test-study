package ch06.timer.v2;

import ch06.timer.v1.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private FakeScheduler fakeScheduler;
    private Calculator calculator;

    @BeforeEach
    void setup() {
        fakeScheduler = new FakeScheduler();
        calculator = new Calculator(fakeScheduler);
    }

    @Test
    void testCalculate1_withFakeScheduler() {
        calculator.calculator1(1, 2, result -> {
            assertEquals(3, result);
        });

        fakeScheduler.advanceTime();
    }
}