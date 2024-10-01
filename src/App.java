
import database.actions.CreateTable;
import entity.Persons;
import entity.Users;
import services.UserRepositoryService;

public class App {
    public static void main(String[] args) throws Exception {

        CreateTable.create(Persons.class);
        CreateTable.create(Users.class);

        Persons persons = new Persons();
        persons.setId(1);
        persons.setName("Cristian");
        persons.setLastname("Moreno");
        persons.setAge(27);
        persons.setDni(123456789);

        Users users = new Users();
        users.setUsername("username");
        users.setPassword("123456789");
        users.setPersons(persons);

        new UserRepositoryService().save(users);

        
    }
}




















