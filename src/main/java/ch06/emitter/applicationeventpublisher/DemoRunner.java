package ch06.emitter.applicationeventpublisher;

import org.springframework.boot.CommandLineRunner;


public class DemoRunner implements CommandLineRunner {
    private final Adder adder;

    public DemoRunner(Adder adder) {
        this.adder = adder;
    }

    @Override
    public void run(final String... args) throws Exception {
        int sum = adder.add(1, 2);
        System.out.println("리턴값:" + sum);
    }
}
