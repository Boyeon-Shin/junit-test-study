package ch01;

class StringAdderTest2 {
    public static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new RuntimeException("expected " + expected + " but got " + actual);
        }
    }

    public static void check(String name, Runnable implementation) {
        try {
            implementation.run();
            System.out.println(name + " PASSED");
        } catch (Exception e) {
            System.err.println(name + " FAILED");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        check("add 1,2", () -> assertEquals(3, StringAdder.sum("1,2")));
        check("add 3,7", () -> assertEquals(10, StringAdder.sum("3, 7")));


    }
}