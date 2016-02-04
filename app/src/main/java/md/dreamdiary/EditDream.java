package md.dreamdiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.app.Activity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageButton;

import com.google.gson.Gson;

public class EditDream extends Activity {

    // The SharedPreferences we'll use to save
    SharedPreferences sprefs;

    // Our User
    public User u;

    Dream dream;

    int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_edit_dream);

        // Read in the shared preferences

        sprefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());

        // Read in the dream

        try {
            dream = (Dream) getIntent().getSerializableExtra("dream");
        }

        catch (NullPointerException e) {

        }

        // Read in the user

        u = (User) getIntent().getSerializableExtra("user");

        index = (int) getIntent().getSerializableExtra("index");

        // Initialize and load the title
        final EditText title = (EditText) findViewById(R.id.view_dream_title_edit);
        title.setText(dream.getTitle());
        title.setEnabled(false);

        // Initialize and load the date
        final EditText date = (EditText) findViewById(R.id.view_dream_date_edit);
        date.setText(dream.getDate());
        date.setEnabled(false);

        // Initialize and load the tags
        final EditText tags = (EditText) findViewById(R.id.view_dream_tags_edit);
        String tempTagString = dream.getTags().toString();
        tempTagString = tempTagString.substring(1, tempTagString.length() - 1);
        tempTagString = tempTagString.replace(",", "");
        tags.setText(tempTagString);
        tags.setEnabled(false);

        // Initialize and load the nouns
        final EditText nouns = (EditText) findViewById(R.id.view_dream_nouns_edit);
        String tempNounString = dream.getNouns().toString();
        tempNounString = tempNounString.substring(1, tempNounString.length() - 1);
        tempNounString = tempNounString.replace(",", "");
        nouns.setText(tempNounString);
        nouns.setEnabled(false);

        // Initialize and load the text of the dream
        final EditText content = (EditText) findViewById(R.id.view_dream_text_edit);
        content.setText(dream.getText());
        content.setEnabled(false);

        // Initialize and load the answer to question 1
        final EditText q1 = (EditText) findViewById(R.id.show_dream_q1_answer);
        q1.setText(dream.getQ1Answer());
        q1.setEnabled(false);

        // Initialize and load the answer to question 2
        final EditText q2 = (EditText) findViewById(R.id.show_dream_q2_answer);
        q2.setText(dream.getQ2Answer());
        q2.setEnabled(false);

        // Initialize and load the answer to question 3
        final EditText q3 = (EditText) findViewById(R.id.show_dream_q3_answer);
        q3.setText(dream.getQ3Answer());
        q3.setEnabled(false);

        // Initialize the checkbox for whether or not the dream is a nightmare
        final CheckBox nightmare = (CheckBox) findViewById(R.id.show_dream_nightmare);
        nightmare.setChecked(dream.getIfNightmare());
        nightmare.setEnabled(false);

        // Initialize the checkbox for whether or not the dream is a repeat
        final CheckBox repeat = (CheckBox) findViewById(R.id.show_dream_repeat);
        repeat.setChecked(dream.getIfRepeat());
        repeat.setEnabled(false);

        // Initialize the edit and save buttons
        final ImageButton edit = (ImageButton) findViewById(R.id.edit_dream_button);
        final ImageButton save = (ImageButton) findViewById(R.id.save_dialog_button);

        // Click the edit button to make the fields editable
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setEnabled(true);
                date.setEnabled(true);
                tags.setEnabled(true);
                nouns.setEnabled(true);
                content.setEnabled(true);
                q1.setEnabled(true);
                q2.setEnabled(true);
                q3.setEnabled(true);
                nightmare.setEnabled(true);
                repeat.setEnabled(true);
            }
        });

        // Click the nightmare checkbox to change whether or not the dream was a nightmare
        nightmare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nightmare.isChecked()) {
                    dream.setNightmare(true);
                } else {
                    dream.setNightmare(false);
                }
            }
        });

        // Click the repeat checkbox to change whether or not the dream was a repeat
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repeat.isChecked()) {
                    dream.setIfRepeat(true);
                }

                else {
                    dream.setIfRepeat(false);
                }
            }
        });

        // Save
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Save all fields
                dream.setTitle(title.getText().toString());
                dream.setDate(date.getText().toString());

                String tagsString = tags.getText().toString();
                String[] tagsArray = tagsString.split(" ");
                dream.clearTags();
                for(int i = 0; i < tagsArray.length; i++) {
                    dream.addTag(tagsArray[i]);
                }

                String nounsString = nouns.getText().toString();
                String[] nounsArray = nounsString.split(" ");
                dream.clearNouns();
                for(int i = 0; i < nounsArray.length; i++) {
                    dream.addNoun(nounsArray[i]);
                }

                dream.setText(content.getText().toString());
                dream.setQ1Answer(q1.getText().toString());
                dream.setQ2Answer(q2.getText().toString());
                dream.setQ3Answer(q3.getText().toString());


                // Insert changed dream into the user then save the user
                u.getList().set(index, dream);
                SharedPreferences.Editor prefsEditor = sprefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(u);
                prefsEditor.putString("MyObject", json);
                prefsEditor.commit();

                Intent go = new Intent(EditDream.this, Home.class);
                startActivity(go);




            }
        });



    }

}
