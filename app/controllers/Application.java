package controllers;

import models.*;
import net.sf.ehcache.search.expression.Not;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;

import views.html.*;

import java.awt.*;
import java.io.File;
import java.io.Reader;
import java.util.ArrayList;
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
        noteBookList = NoteBook.list();
        return showMain(showNoteBookList.render(noteBookList));
    }

    public static Result noteBookAdd() {
        publisherList=Publisher.list();
        noteBookForm=Form.form(NoteBook.class);
        return showMain(noteBookAdd.render(noteBookForm,publisherList));
    }

    public static Result noteBookSave() {
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
        return ok();
    }
    public static Result noteBookUpdate() {
        return ok();
    }

    public static Result noteBookDelete(String id){
        return ok();
    }



}
