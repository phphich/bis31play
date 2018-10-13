package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by COM2-00-PC on 8/28/2018.
 */
@Entity
@Table(name="tbBook")
public class Book  extends  Model{
    @Id
    private String id;
    private  String title, author;
    private int  pages;
    private double price;

    @OneToMany(mappedBy = "book")
    private List<OrdersDetail> ordersDetailList=new ArrayList<OrdersDetail>();
    public List<OrdersDetail> getOrdersDetailList() {
        return ordersDetailList;
    }

    public Book() {
    }

    public Book(String id, String title, String author, int pages, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.price = price;
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

    public static Finder<String, Book> finder =
            new Finder<String, Book>(String.class, Book.class);

    public static void create(Book book){
        book.save();
    }

    public static void update(Book book){
        book.update();
    }

    public static void delete(Book book){
        book.delete();
    }

    public static List<Book> list(){
        return finder.all();
    }

}

