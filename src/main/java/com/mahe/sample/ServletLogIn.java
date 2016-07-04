package com.mahe.sample;

import com.mahe.sample.DataHandler.Driver;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by maha on 28/06/16.
 */
@WebServlet(name = "ServletLogIn")
public class ServletLogIn extends javax.servlet.http.HttpServlet {

    private static final Logger log= Logger.getLogger( ServletLogIn.class.getName() );

    public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        Driver driver = Driver.getInstance();
  //      response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if(password.equals(driver.getPassword(userName)) && !"".equals(userName) && !password.equals("")){
            log.log(Level.FINEST,"login was successful");
            request.setAttribute("login_success",true);
            request.getRequestDispatcher("/Home.jsp").forward(request, response);
        }else{
            log.log(Level.INFO,"login was NOT successful");
            request.setAttribute("login_success",false);
            request.getRequestDispatcher("/LogIn.jsp").forward(request, response);
        }

        out.flush();
    }


}
