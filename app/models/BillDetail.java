package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by COM2-00-PC on 10/13/2018.
 */
@Entity
@Table(name = "tbBillDetail")
public class BillDetail extends Model {
    @ManyToOne
    private Bill bill;
    @ManyToOne
    private Book book;
    private int amount;

    public BillDetail() {
    }

    public BillDetail(Bill bill, Book book, int amount) {
        this.bill = bill;
        this.book = book;
        this.amount = amount;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
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

    public static Finder<String, BillDetail> finder=new Finder<String, BillDetail>(String.class, BillDetail.class);
    public static List<BillDetail> list() {
        return finder.all();
    }

    public static void create(BillDetail ordersDetail){
        ordersDetail.save();
    }
    public static void update(BillDetail ordersDetail){
        ordersDetail.update();
    }
    public static void delete(BillDetail ordersDetail){
        ordersDetail.delete();
    }


}
