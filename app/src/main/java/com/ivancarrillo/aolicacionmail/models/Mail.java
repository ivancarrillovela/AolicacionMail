package com.ivancarrillo.aolicacionmail.models;

import com.ivancarrillo.aolicacionmail.app.App;
import com.ivancarrillo.aolicacionmail.app.Util;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Mail extends RealmObject {
    @PrimaryKey
    private int id;
    private String subject;
    private String message;
    private String senderName;
    private String color;

    public Mail(){}
    public Mail(String subject, String message, String senderName) {
        this.id = App.mailId.incrementAndGet();
        this.subject = subject;
        this.message = message;
        this.senderName = senderName;
        this.color = Util.getRandomColor();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getColor() {
        return color;
    }
}