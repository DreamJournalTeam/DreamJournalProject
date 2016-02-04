package md.dreamdiary;

import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.view.Gravity;

/**
 * Created by michaeldiamond on 12/5/15.
 */
public class MyToast {

    Dream dream;

    public MyToast(Dream d){
        dream = d;
    }

    public static void show(Context context, String text, boolean isLong) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.view_dream_toast, null);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration((isLong) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}