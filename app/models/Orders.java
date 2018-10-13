package models;

import com.avaje.ebean.Expr;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by COM2-00-PC on 10/13/2018.
 */
@Entity
@Table(name = "tbOrders")
public class Orders extends Model {
    @Id
    private String id;
    private Date date;
    private User user;
    private String status;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrdersDetail> ordersDetailList= new ArrayList<OrdersDetail>();
    public List<OrdersDetail> getOrdersDetailList() {
        return ordersDetailList;
    }

    public Orders() {
    }

    public Orders(String id, Date date, User user, String status) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static Model.Finder<String, Orders> finder =
            new Model.Finder<String, Orders>(String.class, Orders.class);

    public static List<Orders> list() {
        return finder.all();
    }
    public static void create(Orders orders){
        orders.save();
    }
    public static void update(Orders orders){
        orders.update();
    }
    public static void delete(Orders orders){
        orders.delete();
    }

}
