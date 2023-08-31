package ua.hryhorenko.springcourse.config.dao;

import org.springframework.stereotype.Component;
import ua.hryhorenko.springcourse.config.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
  private static int PEOPLE_COUNT;
  private List<Person> people;

  {
    people = new ArrayList<>();

    people.add(new Person(++PEOPLE_COUNT, "Tom"));
    people.add(new Person(++PEOPLE_COUNT, "Bob"));
    people.add(new Person(++PEOPLE_COUNT, "Jake"));
    people.add(new Person(++PEOPLE_COUNT, "Mary"));
    people.add(new Person(++PEOPLE_COUNT, "Katy"));
  }

  public List<Person> index() {
    return people;
  }

  public Person show(final int id) {
    return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
  }

}
