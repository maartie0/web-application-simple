import com.mahe.sample.model.HibernateUtil;
import com.mahe.sample.model.Model;
import com.mahe.sample.model.User;
import junit.framework.TestCase;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * Created by maha on 04/07/16.
 */
public class ModelTest extends TestCase{


    public void testUserIsSaved(){
        String username = "bert";
        String password = "dog";
        Session session = HibernateUtil.getSessionFactory().openSession();
        Model model = new Model();
        model.signUp(username,password,password,"agreed");
        assertTrue(userIsInDatabase(username,session));
        assertTrue(userHasCorrectPassword(username,password,session));
    }

    public void testUserIsLoggedIn(){
        String username = "ernie";
        String password = "cat";
        Model model = new Model();
        model.signUp(username,password,password,"agreed");
        assertTrue(model.logIn(username,password));
    }

    public void testNoDoubleUsers(){
        String username = "playdo";
        String password = "test";
        Model model = new Model();
        model.signUp(username,password,password,"agreed");
        assertFalse(model.signUp(username,password,password,"agreed"));
    }



    private boolean userIsInDatabase(String username,Session session){
        Query q = session.createQuery("From User WHERE name=:username").setParameter("username",username);
        List<User> resultList = q.list();
        return (resultList.size() > 0);
    }

    private boolean userHasCorrectPassword(String username, String password,Session session){
        Query q = session.createQuery("From User WHERE name=:username and password=:password")
                .setParameter("username",username)
                .setParameter("password",password);
        List<User> resultList = q.list();
        return (resultList.size() > 0);
    }


}
