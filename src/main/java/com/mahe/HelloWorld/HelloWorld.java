package com.mahe.HelloWorld;

/**
 * Created by maha on 01/07/16.
 */
public class HelloWorld {
    private String message;

    public void setMessage(String message){
        this.message  = message;
    }

    public void getMessage(){
        System.out.println("Your Message : " + message);
    }

}