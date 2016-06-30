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

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        Driver driver = Driver.getInstance();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("userName");
        String password = request.getParameter("psw");
        if(password.equals(driver.getPassword(userName)) && userName != null){ //todo verify user name and password
            System.out.println("login succesfull");
            request.getRequestDispatcher("/Home.jsp").forward(request, response);
        }else{
            System.out.println("login unsuccessfull");
            request.getRequestDispatcher("/LogIn.jsp").forward(request, response);
        }

        out.flush();

    }
}
