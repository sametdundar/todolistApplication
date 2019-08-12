package sametdundar.com.sametdundartodolistapp.models;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String id;
    private String mail;
    private String name;
    private String surname;

    public User() {
    }

    public User(String id, String mail, String name, String surname) {
        this.id = id;
        this.mail = mail;
        this.name = name;
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
