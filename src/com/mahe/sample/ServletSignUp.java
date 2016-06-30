package com.mahe.sample;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by maha on 29/06/16.
 */
@WebServlet(name = "ServletSignUp")
public class ServletSignUp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("userName-SignUp");
        String password = request.getParameter("psw-SignUp");
        String password_verification = request.getParameter("psw-SignUp-verification");
        String radio = request.getParameter("radio");
        if("agreed".equals(radio) && password.equals(password_verification) && !userName.equals("") && !password.equals("") ){
            Driver driver = Driver.getInstance();
            if(driver.setProfile(userName,password)){
                request.getRequestDispatcher("/LogIn.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("/SignUp.jsp").forward(request, response);
            }
        }else{
            request.getRequestDispatcher("/SignUp.jsp").forward(request, response);
        }
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("userName");
        String password = request.getParameter("psw");
        if(password.equals("secret")){
            out.println("hello from get metod, " + userName);
        }else{
            out.println("sorry " + userName + " , that is incorrect. Please refresh and try again");
        }
        out.flush();
    }
}
