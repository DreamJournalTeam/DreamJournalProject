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
//
//        final TextView toastTitle = (TextView) findViewById(R.id.view_dream_title);
//        final TextView toastDate = (TextView) findViewById(R.id.view_dream_date);
//        final TextView toastTags = (TextView) findViewById(R.id.view_dream_tags);
//        final TextView toastNouns = (TextView) findViewById(R.id.view_dream_nouns);
//        final TextView toastContent = (TextView) findViewById(R.id.view_dream_text);
//        final TextView toastq1 = (TextView) findViewById(R.id.view_dream_q1_answer);
//        final TextView toastq2 = (TextView) findViewById(R.id.view_dream_q2_answer);
//        final TextView toastq3 = (TextView) findViewById(R.id.view_dream_q3_answer);
//        final CheckBox nightmare = (CheckBox) findViewById(R.id.view_dream_nightmare);
//        final CheckBox repeat = (CheckBox) findViewById(R.id.view_dream_repeat);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration((isLong) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}