import com.mahe.sample.model.Driver;
import com.mahe.sample.model.User;
import com.mahe.sample.model.HibernateUtil;
import junit.framework.TestCase;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * Created by maha on 04/07/16.
 */
public class HibernateTest extends TestCase {

    public void testProfileSaved(){
        int numOfUsersBefore;
        int numOfUsersAfter;
        String username = "John";
        String password = "sock";

        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q1 = session.createQuery("From User ");
        List<User> resultListBefore = q1.list();
        numOfUsersBefore = resultListBefore.size();

        session.beginTransaction();
        session.save(new User(username,password));
        session.getTransaction().commit();

        Query q2 = session.createQuery("From User ");
        List<User> resultListAfter = q2.list();
        numOfUsersAfter = resultListAfter.size();
        assertEquals(1,numOfUsersAfter-numOfUsersBefore);
        userIsInDatabase(username,session);
        userHasCorrectPassword(username,password,session);
        //todo check that the user is actually added
    }

    private void userIsInDatabase(String username,Session session){
        Query q = session.createQuery("From User WHERE name=:username").setParameter("username",username);
        List<User> resultList = q.list();
        assertTrue(resultList.size() > 0);
    }

    private void userHasCorrectPassword(String username, String password,Session session){
        Query q = session.createQuery("From User WHERE name=:username and password=:password")
                .setParameter("username",username)
                .setParameter("password",password);
        List<User> resultList = q.list();
        assertTrue(resultList.size() > 0);
    }

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
