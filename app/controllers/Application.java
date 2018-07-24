package controllers;

import models.Room;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;

import views.html.*;

import java.io.File;

public class Application extends Controller {

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

        id = myForm.get("id");
        name=myForm.get("name");
        amount=Integer.parseInt(myForm.get("amount"));
        Room myroom= new Room();
        myroom.setId(id);
        myroom.setName(name);
        myroom.setAmount(amount);
        return showMain(showPostRoom.render(myroom));
    }
}
