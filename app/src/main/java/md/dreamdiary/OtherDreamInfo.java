package md.dreamdiary;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by michaeldiamond on 9/16/15.
 */
public class OtherDreamInfo extends Activity {

    Dream dream;

    EditText title;
    String dreamTitle;

    Boolean isNightmare = false;
    CheckBox nightmare;

    Boolean isRepeat;
    CheckBox repeat;

    String [] tagsArray;
    String tagsString;
    EditText tagsInput;
    ArrayList<String> tags;

    String [] nounsArray;
    String nounsString;
    EditText nounsInput;
    ArrayList<String> nouns;

    // The SharedPreferences we'll use to save
    SharedPreferences sprefs;

    // Our User
    public User u;


    Button questions, draw, save, cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_dream_info);
        setTitle("Record other information about your dream");

        // Read in the dream

        try {
            dream = (Dream) getIntent().getSerializableExtra("dream");
        }

        catch (NullPointerException e) {
            System.out.println("testing");
        }


        u = (User) getIntent().getSerializableExtra("user");
        System.out.println(u == null);

        sprefs = getPreferences(MODE_PRIVATE);


        // Create our title and read in previously saved title
        title = (EditText) findViewById(R.id.dream_information_title_edittext);

        // Set the previously saved title
        try {
            title.setText(dream.getTitle());
        }

        catch (NullPointerException e){

        }

        // Set the previously saved tags
        // I'll deal with that later

        // Set the previously saved nouns
        // I'll deal with that later


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

        // Read in whether or not the dream was a repeat
        isRepeat = dream.getIfRepeat();

        // Read in whether the dream is a repeat
        // It starts out checked/unchecked based on whether or not the dream is a repeat
        // What happens if we click the repeat checkbox?
        // If it isn't already checked, we're checking it. So, we're indicating that this is a repeat
        // If it is already checked, we're unchecking it. So, we're indicating that this is not a repeat

        repeat = (CheckBox) findViewById(R.id.dream_information_repeat_checkbox);
        repeat.setChecked(isRepeat);
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

        // Go to the Answer Questions screen
        questions = (Button) findViewById(R.id.activity_dream_info_questions_button);
        questions.setEnabled(true);
        questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dream.setTitle(title.getText().toString());
                dream.setNightmare(isNightmare);
                dream.setIfRepeat(isRepeat);

                // Parse the tag input into separate Strings then add them to the tags arraylist
                tagsString = tagsInput.getText().toString();
                tagsArray = tagsString.split(" ");
                for(int i = 0; i < tagsArray.length; i++) {
                    dream.addTag(tagsArray[i]);
                }

                // Parse the noun input into separate Strings them add them to the nouns arraylist
                nounsString = nounsInput.getText().toString();
                nounsArray = nounsString.split(" ");
                for(int i = 0; i < nounsArray.length; i++) {
                    dream.addNoun(nounsArray[i]);
                }


                Intent go = new Intent(OtherDreamInfo.this, AnswerQuestions.class);
                go.putExtra("dream", dream);
                startActivity(go);
            }
        });

        draw = (Button) findViewById(R.id.activity_dream_info_draw_button);
        draw.setEnabled(true);
        // idk what we're doing with draw yet

        save = (Button) findViewById(R.id.activity_dream_info_save_button);
        save.setEnabled(true);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dream.setTitle(title.getText().toString());
                dream.setNightmare(isNightmare);
                dream.setIfRepeat(isRepeat);

                // Parse the tag input into separate Strings then add them to the tags arraylist
                tagsString = tagsInput.getText().toString();
                tagsArray = tagsString.split(" ");
                for(int i = 0; i < tagsArray.length; i++) {
                    dream.addTag(tagsArray[i]);
                }

                // Parse the noun input into separate Strings them add them to the nouns arraylist
                nounsString = nounsInput.getText().toString();
                nounsArray = nounsString.split(" ");
                for(int i = 0; i < nounsArray.length; i++) {
                    dream.addNoun(nounsArray[i]);
                }

                u.addDream(dream);

                // Save our updated User
                Editor prefsEditor = sprefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(u);
                prefsEditor.putString("u", json);
                prefsEditor.commit();

                Intent go = new Intent(OtherDreamInfo.this, Home.class);
                startActivity(go);
            }

            });



            cancel=(Button)

            findViewById(R.id.activity_dream_info_cancel_button);

            cancel.setEnabled(true);
            cancel.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                startActivity(new Intent(OtherDreamInfo.this, Home.class));
            }
            }

            );
        }

                // What happens if we hit the back button?
                // We want to save as much of this information as possible so we can bring it back up
                // When we come from the record screen
                // Right now, we're only saving the dream text, the title, and the booleans
                // As somewhat of a preliminary test
        @Override
    public void onBackPressed() {
        dream.setTitle(title.getText().toString());
        dream.setNightmare(isNightmare);
        dream.setIfRepeat(isRepeat);

        // Parse the tag input into separate Strings then add them to the tags arraylist
        tagsString = tagsInput.getText().toString();
        tagsArray = tagsString.split(" ");
        for(int i = 0; i < tagsArray.length; i++) {
            dream.addTag(tagsArray[i]);
        }

        // Parse the noun input into separate Strings them add them to the nouns arraylist
        nounsString = nounsInput.getText().toString();
        nounsArray = nounsString.split(" ");
        for(int i = 0; i < nounsArray.length; i++) {
            dream.addNoun(nounsArray[i]);
        }


        Intent go = new Intent(OtherDreamInfo.this, RecordText.class);
        go.putExtra("dream", dream);
        startActivity(go);
    }

}
