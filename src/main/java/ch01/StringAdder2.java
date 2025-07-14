package ch01;

public class StringAdder2 {
    private static int total = 0;

    public static int sum(String numbers) {
        String[] parts = numbers.split(",");
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