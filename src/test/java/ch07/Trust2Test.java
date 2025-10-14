package ch07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Trust2Test {
    String[] namesToTest = {"firstOnly", "first second", ""};

    @Test
    void correctly_finds_out_if_it_is_a_name() {
        for (String name : namesToTest) {
            boolean result = Trust2.isName(name);

            if(name.contains(" ")) {
                assertTrue(result);
            } else  {
                assertFalse(result);
            }
        }
    }

}