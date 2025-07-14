package ch01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringAdderLogging {
    private static int total = 0;
    private static final Logger logger =  LoggerFactory.getLogger(StringAdderLogging.class);;

    public static int sum(String numbers) {
        String[] parts = numbers.split(",");

        logger.info("this is a very important log output - firstNumWas: {}, secondNumWas: {}", parts[0], parts[1]);

        int a = Integer.parseInt(parts[0].trim());
        int b = Integer.parseInt(parts[1].trim());

        int result = a + b;
        total += result;

        return result;
    }

    public int totalSoFar() {
        return total;
    }
}