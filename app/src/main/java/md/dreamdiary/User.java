package md.dreamdiary;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by michaeldiamond on 9/23/15.
 */
public class User implements Serializable{

    ArrayList<Dream> dreams = new ArrayList();
    boolean isFirstLogin = true;

    public User () {

    }

    public ArrayList<Dream> getList() {
        return dreams;
    }

    public void addDream(Dream dream) {
        dreams.add(0, dream);
    }

    public void hasLoggedIn() {
        isFirstLogin = false;
    }
}
