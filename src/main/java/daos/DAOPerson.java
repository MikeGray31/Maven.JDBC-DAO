package daos;

import com.sun.jdi.connect.Connector;
import dtos.Person;
import ConnectionFact.ConnectionFactory;

import java.sql.*;
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM person WHERE id = " + id);
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
            while(rs.next()){ results.add(extractFromResultSet(rs)); }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Person update(Person person) {
        Connection connection = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE user SET first_name=?, last_name=?, email=?, gender=? WHERE id=?");
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.setString(3, person.getEmail());
            ps.setString(4, person.getGender());
            ps.setInt(5, person.getId());
            int i = ps.executeUpdate();
            if(i == 1) return person;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Person create(Person person) {
        Connection connection = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO People VALUES (NULL, ?, ?, ?, ?)");
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.setString(3, person.getEmail());
            ps.setString(4, person.getGender());
            int i = ps.executeUpdate();
            if(i == 1) return person;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Integer id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM user WHERE id=" + id);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
