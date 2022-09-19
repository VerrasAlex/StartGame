package com.example.startgame;

import android.app.Application;

/*
Klash gia na mporoume na vriskoume to mail tou xrhsth opoiodhpote stigmh to xreiazomaste,
mesa apo opoiadhpote klash. */
public class storeMail extends Application {

    String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
        System.out.println("First mail is    " + mail);
    }

}
