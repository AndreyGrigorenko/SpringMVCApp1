package ua.hryhorenko.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.hryhorenko.springcourse.dao.PersonDao;
import ua.hryhorenko.springcourse.models.Person;

@Component
public class PersonValidator implements Validator {
  private final PersonDao personDao;

  @Autowired
  public PersonValidator(PersonDao personDao) {
    this.personDao = personDao;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return Person.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    Person person = (Person) target;

    if (personDao.getPersonByFullName(person.getFullName()).isPresent()) {
      errors.rejectValue("fullName", "", "Person with this name is already exists");
    }
  }
}
