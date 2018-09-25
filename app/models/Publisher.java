package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by COM2-00-PC on 9/25/2018.
 */
@Entity
@Table(name="tbPublisher")
public class Publisher extends Model{
    @Id
    private String id;
    private String name, address;

    @OneToMany(mappedBy="publisher",cascade = CascadeType.ALL)
    private List<NoteBook> noteBookList=new ArrayList<NoteBook>();

    public Publisher() {
    }

    public Publisher(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static Finder<String, Publisher> finder =
            new Finder<String, Publisher>(String.class, Publisher.class);

    public static List<Publisher> list() {
        return  finder.all();
    }
    public static void create(Publisher publisher){
        publisher.save();
    }
    public static void update(Publisher publisher){
        publisher.update();
    }
    public static void delete(Publisher publisher){
        publisher.delete();
    }


}
