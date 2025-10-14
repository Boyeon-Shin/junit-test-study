package ch07;

public record Person(String name, int age, String type) {

    public Person(String name, int age) {
        this(name, age, "person");
    }
}