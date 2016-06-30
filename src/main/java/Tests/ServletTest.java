package Tests;

import com.mahe.sample.Driver;
import com.mahe.sample.ServletLogIn;
import com.mahe.sample.ServletSignUp;
import junit.framework.TestCase;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;





/**
 * Created by maha on 30/06/16.
 */
public class ServletTest extends TestCase{

    private ServletSignUp servletSignUp;
    private ServletLogIn servletLogIn;
    private MockHttpServletRequest request;
   @Autowired private MockHttpServletResponse response;

    @Before
    public void setUp() {
        servletSignUp = new ServletSignUp();
        servletLogIn = new ServletLogIn();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    public void testUserIsAdded() throws ServletException, IOException {
        request.addParameter("userName-SignUp", "scott");
        request.addParameter("psw-SignUp", "tiger");
        request.addParameter("psw-SignUp-verification", "tiger");
        request.addParameter("radio", "agreed");
        servletSignUp.doPost(request, response);
        boolean profileFound = Driver.getInstance().findProfile("scott","tiger");
        assertTrue(profileFound);
        Driver.getInstance().deleteProfile("scott","tiger");
        setUp();
    }

    public void testUserCanLogIn() throws ServletException, IOException {
        String password = "fish";
        String username = "john";
        request.addParameter("userName",username);
        request.addParameter("psw",password);
        Driver.getInstance().setProfile(username,password);
        servletLogIn.doPost(request,response);
        assertTrue((boolean)request.getAttribute("login_success"));
        Driver.getInstance().deleteProfile(username,password);
    }





}
