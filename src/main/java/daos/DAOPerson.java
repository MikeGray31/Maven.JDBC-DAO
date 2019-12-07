package daos;

import dtos.Person;
import ConnectionFact.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOPerson implements DAO {


    public Person extractFromResultSet(ResultSet rs) {
        try {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setFirstName(rs.getString("first_name"));
            person.setLastName(rs.getString("last_name"));
            person.setEmail(rs.getString("email"));
            person.setGender(rs.getString("gender"));

            return person;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Person findById(Integer id) {
        Connection connection = ConnectionFactory.getConnection();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM person WHERE id=" + id);
            if(rs.next()) return extractFromResultSet(rs);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Person> findAll() {
        List<Person> results = new ArrayList<>();
        Connection connection = ConnectionFactory.getConnection();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM person");
            while(rs.next()){
                results.add(extractFromResultSet(rs));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Person update(Object dto) {
        return null;
    }

    public Person create(Object dto) {
        return null;
    }

    public void delete(Integer id) {

    }
}
