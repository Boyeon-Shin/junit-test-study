package ch01;

public class StringAdder {
    public static int sum(String numbers) {
        String[] parts = numbers.split(",");
        int a = Integer.parseInt(parts[0].trim());
        int b = Integer.parseInt(parts[1].trim());
        return a + b;
    }
}