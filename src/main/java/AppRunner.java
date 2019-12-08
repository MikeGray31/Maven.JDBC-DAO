import daos.DAOPerson;
import dtos.Person;

public class AppRunner {

    public static void main(String[] args) {
        Person person = new Person("Hello", "Again", "michaelkgray31@gmail.com", "Male");
        DAOPerson daoPerson = new DAOPerson();
        System.out.println(daoPerson.create(person));
    }
}
