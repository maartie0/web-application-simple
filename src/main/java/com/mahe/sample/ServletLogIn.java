package com.mahe.sample;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by maha on 28/06/16.
 */
@WebServlet(name = "ServletLogIn")
public class ServletLogIn extends javax.servlet.http.HttpServlet {


    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("userName");
        String password = request.getParameter("psw");
        if(password.equals("secret")){
            out.println("hello from get method, " + userName);
        }else{
            out.println("sorry " + userName + " , that is incorrect. Please refresh and try again");
        }
        out.flush();
    }

    public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        Driver driver = Driver.getInstance();

  //      response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("userName");
        String password = request.getParameter("psw");
        if(password.equals(driver.getPassword(userName)) && !userName.equals("") && !password.equals("")){
            System.out.println("login succesfull");
            request.setAttribute("login_success",true);
            request.getRequestDispatcher("/Home.jsp").forward(request, response);
        }else{
            System.out.println("login unsuccessfull");
            request.setAttribute("login_success",false);
            request.getRequestDispatcher("/LogIn.jsp").forward(request, response);
        }

        out.flush();
    }


}
