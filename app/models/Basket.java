package models;

/**
 * Created by COM2-00-PC on 10/13/2018.
 */
public class Basket {
    private Book book;
    private int amount;

    public Basket() {
    }

    public Basket(Book book, int amount) {
        this.book = book;
        this.amount = amount;
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
}
