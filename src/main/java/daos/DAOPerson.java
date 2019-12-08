package daos;

import dtos.Person;
import ConnectionFact.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOPerson implements DAO {

    Connection connection = ConnectionFactory.getConnection();

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
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM People WHERE id = " + id);
            if(rs.next()) return extractFromResultSet(rs);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Person findByInfo(String firstName, String lastName, String email, String gender) {
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM People WHERE first_name = '" + firstName + "' AND " +
                                                                            "last_name = '" + lastName + "' AND " +
                                                                            "email = '" + email + "' AND " +
                                                                            "gender = '" + gender + "'");
            if(rs.next()) return extractFromResultSet(rs);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Person> findAll() {
        List<Person> results = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM People");
            while(rs.next()){ results.add(extractFromResultSet(rs)); }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Person update(Person person) {
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE People SET first_name=?, last_name=?, email=?, gender=? WHERE id=?");
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
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO People (first_name, last_name, email, gender) VALUES (?, ?, ?, ?)");
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

    public Person createWithId(Person person) {
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO People (id, first_name, last_name, email, gender) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, person.getId());
            ps.setString(2, person.getFirstName());
            ps.setString(3, person.getLastName());
            ps.setString(4, person.getEmail());
            ps.setString(5, person.getGender());
            int i = ps.executeUpdate();
            if(i == 1) return person;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Integer id) {
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM People WHERE id=" + id);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
