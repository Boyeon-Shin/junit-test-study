package ch07;

import static ch07.PersonFactory.makePerson;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PersonFactoryTest {
    @Test
    void creates_person_given_passed_in_values () {
        Person factory = makePerson("name", 1);
        assertThat(factory.name()).isEqualTo("name");
        assertThat(factory.age()).isEqualTo(1);
    }
}