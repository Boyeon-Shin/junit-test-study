package ch01;

class StringAdderTest {
        public static void main(String[] args) {
            try {
                int result = StringAdder.sum("1,2");

                if (result == 3) {
                    System.out.println("parserTest example 1 PASSED");
                } else {
                    throw new RuntimeException("parserTest: expected 3 but got " + result);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}