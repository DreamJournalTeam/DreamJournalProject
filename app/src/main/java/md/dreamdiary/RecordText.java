package md.dreamdiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.gson.Gson;
import java.text.DateFormat;
import java.util.Date;
import android.widget.ImageButton;


/**
 * Created by michaeldiamond on 9/14/15.
 */
public class RecordText extends Activity {

    // The SharedPreferences we'll use to save
    SharedPreferences sprefs;

    // Our User
    public User u;

    // Our dream so far
    private Dream dream;

    // What we need for this activity
    private ImageButton save, continue1;
    private EditText dreamText;

    protected void onCreate(Bundle savedInstance) {

        sprefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());

        u = (User) getIntent().getSerializableExtra("user");

        super.onCreate(savedInstance);
        setContentView(R.layout.activity_record_text);
        setTitle("Record your dream");

        // What stores the text of the dream
        dreamText = (EditText) findViewById(R.id.enter_dream_edittext);

        try {
            // Bring in our saved dream
            dream = (Dream) getIntent().getSerializableExtra("dream");
        }

        catch (NullPointerException e) {
            dream = new Dream();
            System.out.println("testing");
        }

        System.out.println(dream.getText());

        dream.setDate(DateFormat.getDateInstance().format(new Date()));

        // Bring in the saved text of the dream
        try {
            dreamText.setText(dream.getText());
        }

        catch (NullPointerException e) {

        }


        // Hit this button to continue working on your dream
        continue1 = (ImageButton) findViewById(R.id.enter_dream_continue_button);

        continue1.setEnabled(true);
        continue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent go = new Intent(RecordText.this, OtherDreamInfo.class);

                try {
                    dream.setText(dreamText.getText().toString());
                }

                catch (NullPointerException e) {

                }

                go.putExtra("dream", dream);
                go.putExtra("user", u);
                startActivity(go);
            }
        });


        save = (ImageButton) findViewById(R.id.enter_dream_save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(RecordText.this, Home.class);

                try {
                    dream.setText(dreamText.getText().toString());
                }

                catch (NullPointerException e) {
                    dream.setText(" ");
                }

                u.addDream(dream);

                Editor prefsEditor = sprefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(u);
                prefsEditor.putString("MyObject", json);
                prefsEditor.commit();

                startActivity(go);
            }
        });
    }

    public void onBackPressed() {
        Intent go = new Intent(RecordText.this, Home.class);
        startActivity(go);
    }

}
