package com.mahe.sample;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by maha on 29/06/16.
 */
@WebServlet(name = "ServletSignUp")
public class ServletSignUp extends HttpServlet {

    private static final Logger log= Logger.getLogger( ServletSignUp.class.getName() );

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("userName-SignUp");
        String password = request.getParameter("password-SignUp");
        String password_verification = request.getParameter("password-SignUp-verification");
        String radio = request.getParameter("radio");
        if(isValidRequest(userName, password, password_verification, radio)){
            doRequest(request, response, userName, password);
        }else{
            request.getRequestDispatcher("/SignUp.jsp").forward(request, response);
            log.log(Level.INFO,"profile was NOT created due to incorrect username/password/verification-password");
        }
        out.flush();
    }

    private void doRequest(HttpServletRequest request, HttpServletResponse response, String userName, String password) throws ServletException, IOException {
        Driver driver = Driver.getInstance();
        if(driver.setProfile(userName,password)){
            request.getRequestDispatcher("/LogIn.jsp").forward(request, response);
            log.log(Level.FINEST,"new profile is created");
        }else{
            request.getRequestDispatcher("/SignUp.jsp").forward(request, response);
            log.log(Level.SEVERE,"ERROR system failed to add new profile, possible cause: profile name was already in use");
            //todo log that failed to create new profile systems fault
        }
    }

    private boolean isValidRequest(String userName, String password, String password_verification, String radio) {
        return "agreed".equals(radio) && password.equals(password_verification) && !userName.equals("") && !password.equals("");
    }
}
