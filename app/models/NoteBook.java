package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by COM2-00-PC on 8/28/2018.
 */
@Entity
@Table(name="tbNoteBook")
public class NoteBook extends  Model{
    @Id
    private String id;
    private  String title, author;
    private int  pages;
    private double price;

    @ManyToOne()
    private Publisher publisher;

    public NoteBook() {
    }

    public NoteBook(String id, String title, String author, int pages, double price, Publisher publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.price = price;
        this.publisher = publisher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public static Finder<String, NoteBook> finder =
            new Finder<String, NoteBook>(String.class, NoteBook.class);

    public static void create(NoteBook nbook){
        nbook.save();
    }

    public static void update(NoteBook nbook){
        nbook.update();
    }

    public static void delete(NoteBook nbook){
        nbook.delete();
    }

    public static List<NoteBook> list(){
        return finder.all();
    }


}
