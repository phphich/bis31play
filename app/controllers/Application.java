package controllers;

import models.*;

import play.api.templates.Html;
import play.cache.Cache;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;

import views.html.*;

import java.awt.*;
import java.io.File;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Application extends Controller {

    //Book
    public static List<Book> bookList = new ArrayList<Book>();
    public static Form<Book> bookForm = Form.form(Book.class);
    public static Book book;

    //Publisher
    public static List<Publisher> publisherList;

    //NoteBook
    public static List<NoteBook> noteBookList = new ArrayList<NoteBook>();
    public static Form<NoteBook> noteBookForm= Form.form(NoteBook.class);
    public static NoteBook noteBook;

    public static Result showMain(Html content){
        return ok(main.render(content));
    }

    public static Result home() {
        String name = "Phichayaphak";
        String surname  ="Phiphitphatphaisit";
        return showMain(home.render(name, surname));
    }

    public static Result secondpage(){
        return showMain(secondpage.render());
    }

    public static Result showRoom() {
        Room room1=new Room();
        room1.setId("com1");
        room1.setName("ห้องปฏิบัติการคอมพิวเตอร์ 1");
        room1.setAmount(50);

        Room room2 = new Room("coom2","ห้องปฏิบัติการคอมพิวเตอร์ 2", 80);

        return showMain(showRoom.render(room1, room2));
    }

    public static Result getData() {
        return showMain(getData.render());
    }

    public static Result postData(){
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("profile") ;

        if (picture != null) {
            String fileName = picture.getFilename();
            String contentType = picture.getContentType();
            File f = picture.getFile();
            File newFile = new File("/assets/images/upload/" + f);
            return redirect("/");
        } else {
            flash("error", "Missing file");
            return redirect("/getData");
        }
    }

    public static Result inputRoom() {
        return showMain(inputRoom.render());
    }

    public static Result postRoom(){
        DynamicForm myForm = Form.form().bindFromRequest();
        String id, name;
        int amount;
        String color;

        id = myForm.get("id");
        name=myForm.get("name");
        amount=Integer.parseInt(myForm.get("amount"));
        color = myForm.get("color");
        return ok(info.render(color));
/*
        Room myroom= new Room();
        myroom.setId(id);
        myroom.setName(name);
        myroom.setAmount(amount);
        return showMain(showPostRoom.render(myroom)); */
    }

    public static Form<Room2> roomForm = Form.form(Room2.class);
    public static List<Room2> roomList = new ArrayList<Room2>();
    public static  Room2 room;


    public static Result inputRoomHelper(){
        return showMain(inputRoomHelper.render(roomForm));
    }

    public static Result postRoomHelper() {
        Form<Room2> newRoom = roomForm.bindFromRequest();
        if (newRoom.hasErrors()){
            return showMain(inputRoomHelper.render(newRoom));
        }
        else {
            room = newRoom.get();
            roomList.add(room);
            return  showRoomHelper();
        }
    }

    public static Result showRoomHelper() {
        return showMain(showRoomHelper.render(roomList));
    }

    public static Result showOneRoom (String id){
        //return ok(info.render(id));
        for (int i=0;i<roomList.size();i++){
            if(roomList.get(i).getId().equals(id) ){
                room = roomList.get(i);
                break;
            }
        }
        return showMain(showOneRoom.render(room));
    }

    public static Result sampleBook(){
        book = new Book("B001","Dynamic Web", "Phich",150, 50000.00);
        Book.create(book);
        book = new Book("B002","Java Programming", "John",150, 10000.00);
        Book.create(book);
        book = new Book("B003","VB Programming", "Phich",150, 30000.00);
        Book.create(book);
        book = new Book("B004","C# Programming", "Phich",150, 100000.00);
        Book.create(book);
        book = new Book("B005","PHP Programming", "Phich",150, 5000.00);
        Book.create(book);

        return showBookList();
    }

    public static Result showBookList() {
        bookList= Book.list();
        return showMain(showBookList.render(bookList));
    }

    public static Result bookAdd() {
        bookForm=Form.form(Book.class);
        return showMain(bookAdd.render(bookForm));
    }
    public static Result bookSave() {
        Form<Book> newBook = bookForm.bindFromRequest();
        if(newBook.hasErrors()){
            flash("msgError", "ป้อนข้อมูลไม่สมบูรณ์/ไม่ถูกต้อง กรุณาตรวจสอบและแก้ไขใหม่");
            return showMain(bookAdd.render(newBook));
        }else{
            book = newBook.get();
            Book bcheck = Book.finder.byId(book.getId());
            if(bcheck!=null){
            //if(Book.finder.byId(book.getId())!=null){
                flash("msgError", "รหัสที่กำหนดมีอยู่แล้วในระบบ กรุณาใช้ค่าอื่น");
                return showMain(bookAdd.render(newBook));
            }else{
                Book.create(book);
                return showBookList();
            }
        }
    }

    public static Result bookEdit(String id) {
        book = null;
        book = Book.finder.byId(id);
        if(book != null){
            bookForm=Form.form(Book.class).fill(book);
            return showMain(bookEdit.render(bookForm));
        }else {
            return showBookList();
        }
    }

    public static Result bookUpdate(){
        Form<Book> newBook = bookForm.bindFromRequest();
        if(newBook.hasErrors()){
            flash("msgError", "ป้อนข้อมูลไม่สมบูรณ์/ไม่ถูกต้อง กรุณาตรวจสอบและแก้ไขใหม่");
            return showMain(bookEdit.render(newBook));
        }else{
            book = newBook.get();
            Book.update(book);
            return showBookList();
        }
    }

    public static Result bookDelete(String id) {
        book = null;
        book = Book.finder.byId(id);
        if(book != null){
            Book.delete(book);
        }
        return showBookList();
    }

    public static Result showNoteBookList() {
        if(session().get("ustatus") ==null) {
            flash("errPermission", "ท่านต้องลงชื่อเข้าใช้ก่อน");
            return showMain(home.render("Phich", "Phiphitphatphaisit"));
        }else if(!session().get("ustatus").equals("Admin")){
            flash("errPermission", "ท่านไม่มีสิทธิ์ใช้ในส่วนนี้");
            return showMain(home.render("Phich", "Phiphitphatphaisit"));
        }

        noteBookList = NoteBook.list();
        return showMain(showNoteBookList.render(noteBookList));
    }

    public static Result noteBookAdd() {
        if(session().get("ustatus") ==null) {
            flash("errPermission", "ท่านต้องลงชื่อเข้าใช้ก่อน");
            return showMain(home.render("Phich", "Phiphitphatphaisit"));
        }else if(!session().get("ustatus").equals("Admin")){
            flash("errPermission", "ท่านไม่มีสิทธิ์ใช้ในส่วนนี้");
            return showMain(home.render("Phich", "Phiphitphatphaisit"));
        }

        publisherList=Publisher.list();
        noteBookForm=Form.form(NoteBook.class);
        return showMain(noteBookAdd.render(noteBookForm,publisherList));
    }

    public static Result noteBookSave() {
        if(session().get("ustatus") ==null) {
            flash("errPermission", "ท่านต้องลงชื่อเข้าใช้ก่อน");
            return showMain(home.render("Phich", "Phiphitphatphaisit"));
        }else if(!session().get("ustatus").equals("Admin")){
            flash("errPermission", "ท่านไม่มีสิทธิ์ใช้ในส่วนนี้");
            return showMain(home.render("Phich", "Phiphitphatphaisit"));
        }

        publisherList=Publisher.list();
        Form<NoteBook> newBook = noteBookForm.bindFromRequest();
        if(newBook.hasErrors()){
            flash("msgError", "ป้อนข้อมูลไม่สมบูรณ์/ไม่ถูกต้อง กรุณาตรวจสอบและแก้ไขใหม่");
            return showMain(noteBookAdd.render(newBook, publisherList));
        }else{
            noteBook = newBook.get();
            NoteBook nbcheck = NoteBook.finder.byId(noteBook.getId());
            if(nbcheck!=null){
                flash("msgError", "รหัสที่กำหนดมีอยู่แล้วในระบบ กรุณาใช้ค่าอื่น");
                return showMain(noteBookAdd.render(newBook,publisherList));
            }else{
                NoteBook.create(noteBook);
                return showNoteBookList();
            }
        }
    }

    public static Result noteBookEdit(String id) {
        if(session().get("ustatus") ==null) {
            flash("errPermission", "ท่านต้องลงชื่อเข้าใช้ก่อน");
            return showMain(home.render("Phich", "Phiphitphatphaisit"));
        }else if(!session().get("ustatus").equals("Admin")){
            flash("errPermission", "ท่านไม่มีสิทธิ์ใช้ในส่วนนี้");
            return showMain(home.render("Phich", "Phiphitphatphaisit"));
        }

        publisherList=Publisher.list();
       noteBook=NoteBook.finder.byId(id);
       if(noteBook!=null){
           noteBookForm = Form.form(NoteBook.class).fill(noteBook);
           return showMain(noteBookEdit.render(noteBookForm, publisherList));
       }else{
           return showNoteBookList();
       }
    }
    public static Result noteBookUpdate() {
        if(session().get("ustatus") ==null) {
            flash("errPermission", "ท่านต้องลงชื่อเข้าใช้ก่อน");
            return showMain(home.render("Phich", "Phiphitphatphaisit"));
        }else if(!session().get("ustatus").equals("Admin")){
            flash("errPermission", "ท่านไม่มีสิทธิ์ใช้ในส่วนนี้");
            return showMain(home.render("Phich", "Phiphitphatphaisit"));
        }

        publisherList=Publisher.list();
        Form<NoteBook> newBook = noteBookForm.bindFromRequest();
        if(newBook.hasErrors()){
            flash("msgError", "ป้อนข้อมูลไม่สมบูรณ์/ไม่ถูกต้อง กรุณาตรวจสอบและแก้ไขใหม่");
            return showMain(noteBookEdit.render(newBook, publisherList));
        }else{
            noteBook = newBook.get();
            NoteBook.update(noteBook);
            return showNoteBookList();
        }
    }

    public static Result noteBookDelete(String id){
        if(session().get("ustatus") ==null) {
            flash("errPermission", "ท่านต้องลงชื่อเข้าใช้ก่อน");
            return showMain(home.render("Phich", "Phiphitphatphaisit"));
        }else if(!session().get("ustatus").equals("Admin")){
            flash("errPermission", "ท่านไม่มีสิทธิ์ใช้ในส่วนนี้");
            return showMain(home.render("Phich", "Phiphitphatphaisit"));
        }

        noteBook=NoteBook.finder.byId(id);
        if(noteBook!=null){
            NoteBook.delete(noteBook);
        }
        return showNoteBookList();
    }

    public static Result authen(){
        DynamicForm myForm = Form.form().bindFromRequest();
        String uid = myForm.get("uid");
        String upass = myForm.get("upass");
        User user = User.authen(uid,upass);
        if(user==null){
            flash("errLogin", "Invalid id or password");
        }else{
            session("uid", user.getId());
            session("uname", user.getName());
            session("ustatus", user.getStatus());
        }
        return showMain(home.render("Phich", "Phiphitphatphaisit"));
    }

    public static Result logout() {
        session().clear();
        return showMain(home.render("Phich", "Phiphitphatphaisit"));
    }

    public List<OrdersDetail> basketList = new ArrayList<OrdersDetail>();

    public static Result showBookSale() {
        bookList=Book.list();
        List<Basket> basketList = (List<Basket>) Cache.get("basketList");

        return showMain(showBookSale.render(bookList, basketList));
    }

    public static Result addOrder(String id ){
        List<Basket> basketList=new ArrayList<Basket>();
        boolean found=false;

        if(Cache.get("basketList") != null){
            basketList.addAll((List<Basket>) Cache.get("basketList"));
            for(int i=0;i<basketList.size();i++) {
                if(basketList.get(i).getBook().getId().equals(id)) {
                    int amount =  basketList.get(i).getAmount();
                    basketList.get(i).setAmount(amount + 1);
                    found=true;
                    break;
                }
            }
        }

        if(found==false) {
            book = Book.finder.byId(id);
            basketList.add(new Basket(book,1));
        }

        Cache.set("basketList", basketList);
        return redirect("/showBookSale");
    }

    public static Result removeItem(String id){
        List<Basket> basketList=new ArrayList<Basket>();

        if(Cache.get("basketList") != null){
            basketList.addAll((List<Basket>) Cache.get("basketList"));
            for(int i=0;i<basketList.size();i++) {
                if(basketList.get(i).getBook().getId().equals(id)) {
                    basketList.remove(i);
                    break;
                }
            }
        }

        Cache.set("basketList", basketList);
        return redirect("/showBookSale");
    }

    public static Result checkBill(){
        List<Basket> basketList=new ArrayList<Basket>();
        if(Cache.get("basketList") != null) {
            basketList =(List<Basket>) Cache.get("basketList");
        }
        return showMain(checkBill.render(basketList));
    }

    public static Result saveBill(){
        List<Basket> basketList=new ArrayList<Basket>();

        if(Cache.get("basketList") != null) {
            Bill bill=new Bill();
            User user = User.finder.byId(session().get("uid"));
            bill.setDate(new Date());
            bill.setUser(user);
            bill.create(bill);

            basketList=(List<Basket>) Cache.get("basketList");
            for(int i=0; i<basketList.size();i++) {
                BillDetail billDetail = new BillDetail();
                billDetail.setBill(bill);
                billDetail.setBook(basketList.get(i).getBook());
                billDetail.setAmount(basketList.get(i).getAmount());
                BillDetail.create(billDetail);

            }
        }
        Cache.remove("basketList");
        return redirect("/showBookSale");

    }




}
