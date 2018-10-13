package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by COM2-00-PC on 10/13/2018.
 */
@Entity
@Table(name = "tbBill")
public class Bill extends Model {
    @Id
    private String id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne
    private User user;
    private String status;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private static List<BillDetail> billDetailList=new ArrayList<BillDetail>();

    public static List<BillDetail> getBillDetailList() {
        return billDetailList;
    }

    public Bill() {
        setId();
    }

    public Bill(String id, Date date, User user, String status) {
        setId();
        this.date = date;
        this.user = user;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        int n;
        Random random = new Random();
        n = random.nextInt(100000)+1;
        id = Integer.toString(n);
    }

    public String getDate() {
        DateFormat dt= new SimpleDateFormat("dd/MM/yyyy [hh:mm:ss]");
        return dt.format(date) ;
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

    public static Finder<String, Bill> finder=new Finder<String, Bill>(String.class, Bill.class);
    public static List<Bill> list(){
        return finder.all();
    }
    public static void create(Bill bill){
        bill.save();
    }
    public static void update(Bill bill){
        bill.update();
    }
    public static void delete(Bill bill){
        bill.delete();
    }
}
