package md.dreamdiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.gson.Gson;

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
    private Button save, changeEnding, continue1;
    private EditText dreamText;

    protected void onCreate(Bundle savedInstance) {

        sprefs = getPreferences(MODE_PRIVATE);

        u = (User) getIntent().getSerializableExtra("user");

        super.onCreate(savedInstance);
        setContentView(R.layout.activity_record_text);
        setTitle("Record your dream");

        // What stores the text of the dream
        dreamText = (EditText) findViewById(R.id.enter_dream_edittext);

        try {
            // Bring in our saved dream
            System.out.println("abc");
            dream = (Dream) getIntent().getSerializableExtra("dream");
            System.out.println(dream.getIfNightmare() + "asgafhfsg");
        }

        catch (NullPointerException e) {
            dream = new Dream();
            System.out.println("testing");
        }

        System.out.println(dream.getText());

        // Bring in the saved text of the dream
        try {
            dreamText.setText(dream.getText());
        }

        catch (NullPointerException e) {

        }


        // Hit this button to save your dream
        continue1 = (Button) findViewById(R.id.enter_dream_continue_button);

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

        // I still have to figure out how to actually save the data
        save = (Button) findViewById(R.id.enter_dream_save_button);
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

                // Save our updated User
                Editor prefsEditor = sprefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(u);
                prefsEditor.putString("u", json);
                prefsEditor.commit();

                startActivity(go);
            }
        });


        changeEnding = (Button) findViewById(R.id.enter_dream_change_ending_button);
    }

    public void onBackPressed() {
        Intent go = new Intent(RecordText.this, Home.class);
        startActivity(go);
    }

}
