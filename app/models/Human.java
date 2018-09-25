package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by COM2-00-PC on 9/11/2018.
 */
@Entity
@Table(name = "tbHuman")
public class Human extends Model{
    @Id
    private String id;
    private String name, surname;
    private boolean gender;

    public Human() {
    }

    public Human(String id, String name, String surname, boolean gender) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public static Finder<String, Human> find = new Finder<String, Human>(String.class, Human.class);



}
