import com.mahe.sample.DataHandler.Department;
import com.mahe.sample.DataHandler.User;
import com.mahe.sample.DataHandler.HibernateUtil;
import junit.framework.TestCase;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by maha on 04/07/16.
 */
public class HibernateTest extends TestCase {

    public void testProfileSaved(){
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Department department = new Department("java");
        session.save(department);

        session.save(new User("Jakab Gipsz","eel",department));
        session.save(new User("Captain Nemo","peacock",department));

        session.getTransaction().commit();

        Query q = session.createQuery("From User ");

        List<User> resultList = q.list();
        System.out.println("num of employess:" + resultList.size());
        for (User next : resultList) {
            System.out.println("next employee: " + next);
            }

    }

}
