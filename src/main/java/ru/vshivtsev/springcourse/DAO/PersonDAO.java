package ru.vshivtsev.springcourse.DAO;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.vshivtsev.springcourse.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<Person>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;
    }

    public Person show (int id){
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return null;
    }

    public void Save(Person person){
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);

        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO person VALUES(" + 1 + ",'" + person.getName()
                    + "'," + person.getAge() + ",'" + person.getEmail() + "')";

            statement.executeUpdate(SQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Update(int id, Person person){
//        Person personToBeUpdated = show(id);
//
//        personToBeUpdated.setName(person.getName());
//        personToBeUpdated.setAge(person.getAge());
//        personToBeUpdated.setEmail(person.getEmail());
    }

    public void Delete(int id){
//        people.removeIf(p -> p.getId() == id);
    }
}
