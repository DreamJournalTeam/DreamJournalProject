package md.dreamdiary;

/**
 * Created by Matthew on 11/18/15.
 */

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DreamAdapter extends ArrayAdapter<String> implements View.OnClickListener {

    public static ArrayList<String> list = new ArrayList<String>();
    public DreamAdapter(Context context, int resource) {
        super(context, resource);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
    }


    @Override
    public void onClick(View v) {

    }
}
