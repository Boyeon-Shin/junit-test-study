package ch05.stub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JestReturnValuesTest {

    @Mock
    Supplier<String> stubFunc;

    @Test
    void fakeSameReturnValues() {
        given(stubFunc.get()).willReturn("abc");

        // 값이 동일하게 유지됨
        assertEquals("abc", stubFunc.get());
        assertEquals("abc", stubFunc.get());
        assertEquals("abc", stubFunc.get());
    }

    @Test
    void fakeMultipleReturnValues() {
        given(stubFunc.get()).willReturn("a", "b", "c", null);
        assertEquals("a", stubFunc.get());
        assertEquals("b", stubFunc.get());
        assertEquals("c", stubFunc.get());
        assertEquals(null, stubFunc.get()); // JS의 undefined 에 해당
    }
}