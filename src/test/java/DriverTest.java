import com.mahe.sample.DataHandler.Driver;
import junit.framework.TestCase;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by maha on 30/06/16.
 */
public class DriverTest extends TestCase {


    public void testNoDoubleUsers() throws ServletException, IOException {
        String username = "tim";
        String password = "eel";
        Driver driver = Driver.getInstance();
        driver.deleteProfile(username,password);
        boolean firstUser = driver.setProfile(username,password);
        boolean secondUser = driver.setProfile(username,password);
        assertTrue(firstUser);
        assertFalse(secondUser);
        driver.deleteProfile(username,password);
    }

    public void testDeleteUser(){
        String username = "pete";
        String password = "platypus";
        Driver driver = Driver.getInstance();
        boolean added = driver.setProfile(username,password);
        boolean deleted = driver.deleteProfile(username,password);
        assertTrue(added);
        assertTrue(deleted);
    }

    public void testFindPassword(){
        String password = "horse";
        String username = "mike";
        Driver driver = Driver.getInstance();
        boolean added = driver.setProfile(username,password);
        String response = driver.getPassword(username);
        assertTrue(added);
        assertEquals(password,response);
        driver.deleteProfile(username,password);
    }

    public void testUserIsAddedFoundDeletedFindPassword(){
        String password = "chicken";
        String username = "tom";
        Driver driver = Driver.getInstance();
        boolean added = driver.setProfile(username,password);
        boolean found = driver.findProfile(username,password);
        String response = driver.getPassword(username);
        boolean deleted = driver.deleteProfile(username,password);
        assertTrue(added);
        assertTrue(found);
        assertEquals(password,response);
        assertTrue(deleted);
    }

    public void testUserCantBeFoundAfterDeleted(){
        String password = "dog";
        String username = "rory";
        Driver driver = Driver.getInstance();
        boolean added = driver.setProfile(username,password);
        boolean deleted = driver.deleteProfile(username,password);
        boolean found = driver.findProfile(username,password);
        assertTrue(added);
        assertTrue(deleted);
        assertFalse(found);
    }

    public void testUserCanBeReAddedAfterDeleted(){
        String password = "cat";
        String username = "laura";
        Driver driver = Driver.getInstance();
        boolean added = driver.setProfile(username,password);
        boolean deleted = driver.deleteProfile(username,password);
        boolean added2 = driver.setProfile(username,password);
        assertTrue(added);
        assertTrue(deleted);
        assertTrue(added2);
        driver.deleteProfile(username,password);
    }



}
