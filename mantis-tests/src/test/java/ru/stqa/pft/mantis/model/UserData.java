package ru.stqa.pft.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by User on 01.06.2016.
 */

@Entity
@Table(name = "mantis_user_table")
public class UserData {

    @Column(name = "username")
    @Type(type = "string")
    private String username;

    @Transient
    private String password;

    @Column(name = "email")
    @Type(type = "string")
    private String email;

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    public String getUsername() {
        return username;
    }

    public UserData withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserData withPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }


    public int getId() {
        return id;
    }

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
