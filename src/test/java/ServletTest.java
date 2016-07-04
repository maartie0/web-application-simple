import com.mahe.sample.DataHandler.Driver;
import com.mahe.sample.ServletLogIn;
import com.mahe.sample.ServletSignUp;
import junit.framework.TestCase;
import org.junit.Before;
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
        String username = "scotty";
        String password = "doesnt know";
        request.addParameter("userName-SignUp", username);
        request.addParameter("password-SignUp", password);
        request.addParameter("password-SignUp-verification", password);
        request.addParameter("radio", "agreed");
        servletSignUp.doPost(request, response);
        boolean profileFound = Driver.getInstance().findProfile(username,password);
        assertTrue(profileFound);
        Driver.getInstance().deleteProfile(username,password);
        setUp();
    }

    public void testUserCanLogIn() throws ServletException, IOException {
        String password = "fish";
        String username = "john";
        request.addParameter("userName",username);
        request.addParameter("password",password);
        Driver.getInstance().setProfile(username,password);
        servletLogIn.doPost(request,response);
        assertTrue((boolean)request.getAttribute("login_success"));
        Driver.getInstance().deleteProfile(username,password);
        setUp();
    }





}
