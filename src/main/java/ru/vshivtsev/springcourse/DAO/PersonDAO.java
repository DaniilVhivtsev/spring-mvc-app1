package ru.vshivtsev.springcourse.DAO;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.vshivtsev.springcourse.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom", "tom@mail.ru", 24));
        people.add(new Person(++PEOPLE_COUNT, "Bob", "bob@mail.ru", 52));
        people.add(new Person(++PEOPLE_COUNT, "Mike", "mike@mail.ru", 18));
        people.add(new Person(++PEOPLE_COUNT, "Katy", "katy@mail.ru", 34));
    }

    public List<Person> index() {
        return people;
    }

    public Person show (int id){return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);}

    public void Save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void Update(int id, Person person){
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(person.getName());
        personToBeUpdated.setAge(person.getAge());
        personToBeUpdated.setEmail(person.getEmail());
    }

    public void Delete(int id){
        people.removeIf(p -> p.getId() == id);
    }
}
