package md.dreamdiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by michaeldiamond on 9/16/15.
 */
public class OtherDreamInfo extends Activity {

    Dream dream;

    EditText title;
    String dreamTitle;

    Boolean isNightmare;
    CheckBox nightmare;

    Boolean isRepeat;
    CheckBox repeat;

    EditText tagsInput;
    ArrayList<String> tags;

    EditText nounsInput;
    ArrayList<String> nouns;


    Button questions, draw, save, cancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_dream_info);
        setTitle("Record other information about your dream");

        // Read in the dream
        dream = (Dream) getIntent().getSerializableExtra("dream");

        // Create our title and read in previously saved title
        title = (EditText) findViewById(R.id.dream_information_title_edittext);

        try {
            title.setText(dream.getTitle());
        }

        catch (NullPointerException e){

        }

        // Read in whether the dream was a nightmare

        isNightmare = dream.getIfNightmare();


        // This is our check box to see if it's a nightmare
        // It starts out checked/unchecked based on whether or not the dream is a nightmare
        // What happens if we click the nightmare checkbox?
        // If it isn't already checked, we're checking it. So, we're indicating that this is a nightmare
        // If it is already checked, we're unchecking it. So, we're indicating that this is not a nightmare
        nightmare = (CheckBox) findViewById(R.id.dream_information_nightmare_checkbox);
        nightmare.setChecked(isNightmare);

        nightmare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                if(!nightmare.isChecked()) {
                    isNightmare = true;
                }

                else {
                    isNightmare = false;
                }
            }
        });

        isRepeat = dream.getIfRepeat();
        repeat.setChecked(isRepeat);

        repeat = (CheckBox) findViewById(R.id.dream_information_repeat_checkbox);
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                if(!repeat.isChecked()) {
                    isRepeat = true;
                }

                else {
                    isRepeat = false;
                }
            }
        });

        // Our tags -- this will probably become a dropdown menu later
        tagsInput = (EditText) findViewById(R.id.dream_information_tags_edittext);

        // Our people/places -- this will probably become a dropdown menu later
        nounsInput = (EditText) findViewById(R.id.dream_information_nouns_edittext);

        questions = (Button) findViewById(R.id.activity_dream_info_questions_button);
        questions.setEnabled(true);
        questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtherDreamInfo.this, AnswerQuestions.class));
            }
        });

        draw = (Button) findViewById(R.id.activity_dream_info_draw_button);
        draw.setEnabled(true);
        // idk what we're doing with draw yet

        save = (Button) findViewById(R.id.activity_dream_info_save_button);
        save.setEnabled(true);
        // idk what we're doing with save yet
        // At least going home, but how are we saving the information?


        cancel = (Button) findViewById(R.id.activity_dream_info_cancel_button);
        cancel.setEnabled(true);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtherDreamInfo.this, Home.class));
            }
        });
    }

    // What happens if we hit the back button?
    // We want to save as much of this information as possible so we can bring it back up
    // When we come from the record screen
    // Right now, we're only saving the dream text, the title, and the booleans
    // As somewhat of a preliminary test
    @Override
    public void onBackPressed() {
        dreamTitle = title.getText().toString();
        Intent go = new Intent(OtherDreamInfo.this, RecordText.class);
        go.putExtra("dream", dream);
        startActivity(go);

    }

}
