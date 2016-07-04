package com.mahe.sample.model;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.test.web.ModelAndViewAssert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by maha on 04/07/16.
 */
public class Model {

    public boolean signUp(String username, String password, String passwordVerification, String radio){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(isValidRequest(username,password,passwordVerification,radio) && !userAlreadyExists(username,session)){
            setProfile(username,password,session);
            return true;
        }
        return false;
    }

    public boolean logIn(String username,String password){ //todo might want to return a user object instead of boolean
        Session session = HibernateUtil.getSessionFactory().openSession();
        return userHasCorrectPassword(username, password, session);
    }



    private boolean userHasCorrectPassword(String username, String password,Session session){
        Query q = session.createQuery("From User WHERE name=:username and password=:password")
                .setParameter("username",username)
                .setParameter("password",password);
        List<User> resultList = q.list();
        return resultList.size() == 1 && resultList.iterator().next().equals(username, password);
    }

    private boolean isValidRequest(String userName, String password, String password_verification, String radio) {
        return "agreed".equals(radio) && password.equals(password_verification) && !"".equals(userName) && !"".equals(password);
    }

    private void setProfile(String username, String password,Session session) {
        session.beginTransaction();
        session.save(new User(username,password));
        session.getTransaction().commit();
    }

    private boolean userAlreadyExists(String username,Session session){
        Query q = session.createQuery("From User WHERE name=:username")
                .setParameter("username",username);
        List<User> resultList = q.list();
        return resultList.size() > 0 && resultList.iterator().next().getName().equals(username);
    }

}
