package models;

import javax.validation.OverridesAttribute;
import java.util.Random;

/**
 * Created by COM2-00-PC on 7/10/2018.
 */
public class Room2 extends Room {
    public Room2() {
        setId();
    }
    public Room2(String name, int amount) {
        super(null, name, amount);
        setId();
    }


    protected void setId() {
        int random;
        Random ran = new Random();
        random = ran.nextInt(100000)+1;
        super.id = Integer.toString(random);
    }
}
