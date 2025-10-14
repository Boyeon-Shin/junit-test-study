package ch07;

public class PersonFactory {
    public static Person makePerson(String name, int age) {
        return new Person(name, age);
    }
}