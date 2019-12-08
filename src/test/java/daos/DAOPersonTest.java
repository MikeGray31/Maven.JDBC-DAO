package daos;

import dtos.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DAOPersonTest {
    private DAOPerson daoPerson;

    @Before
    public void doThisFirst(){
        daoPerson = new DAOPerson();
    }

    @Test
    public void testDelete(){
        Person newGuy = new Person("NewFirstName", "NewLastName", "aNewEmail", "Male");
        Person actual1 = daoPerson.create(newGuy);
        Integer id = daoPerson.findByInfo("NewFirstName", "NewLastName", "aNewEmail", "Male").getId();
        daoPerson.delete(id);
        Assert.assertNull(daoPerson.findById(id));
    }

    @Test
    public void testFindById() {
        String expectedFirst = "Clyve";
        String expectedLast = "Fourcade";
        String expectedEmail = "cfourcade0@xing.com";
        String expectedGender = "Male";
        Person actual = daoPerson.findById(1);

        Assert.assertEquals(expectedFirst, actual.getFirstName());
        Assert.assertEquals(expectedLast, actual.getLastName());
        Assert.assertEquals(expectedEmail, actual.getEmail());
        Assert.assertEquals(expectedGender, actual.getGender());
    }

    @Test
    public void testCreate(){
        Person newGuy = new Person(Integer.MIN_VALUE,"First", "Last", "anEmail", "Male");
        Person actual = daoPerson.createWithId(newGuy);
        Assert.assertEquals(newGuy, actual);
        daoPerson.delete(newGuy.getId());
    }

    @Test
    public void testUpdate(){
        Person newGuy = new Person(Integer.MAX_VALUE,"NotUpdated", "Last", "anEmail", "Male");
        daoPerson.createWithId(newGuy);
        newGuy.setFirstName("Updated");
        daoPerson.update(newGuy);
        Assert.assertEquals(newGuy.getFirstName(), daoPerson.findById(Integer.MAX_VALUE).getFirstName());
        daoPerson.delete(Integer.MAX_VALUE);
    }
}
