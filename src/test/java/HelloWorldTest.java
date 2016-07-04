/**
 * Created by maha on 01/07/16.
 */

import com.mahe.HelloWorld.HelloWorld;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldTest extends TestCase {

    public void testHelloWorld(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("file:src/beans.xml");

        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");

        obj.getMessage();
    }

}
