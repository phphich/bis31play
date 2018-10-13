package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by COM2-00-PC on 10/13/2018.
 */
@Entity
@Table(name = "tbOrdersDetail")
public class OrdersDetail extends Model {
    @Id
    private String id;
    @ManyToOne
    private Orders orders;
    @ManyToOne
    private Book book;
    private int amount;

    public OrdersDetail() {
    }

    public OrdersDetail(String id, Orders orders, Book book, int amount) {
        this.id = id;
        this.orders = orders;
        this.book = book;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static Finder<String, OrdersDetail> finder =
            new Finder<String, OrdersDetail>(String.class, OrdersDetail.class);

    public static List<OrdersDetail> list() {
        return finder.all();
    }
    public static void create(OrdersDetail ordersDetail){
        ordersDetail.save();
    }
    public static void update(OrdersDetail ordersDetail){
        ordersDetail.update();
    }
    public static void delete(OrdersDetail ordersDetail){
        ordersDetail.delete();
    }

    
}
