package md.dreamdiary;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.google.gson.Gson;

/**
 * Created by michaeldiamond on 9/17/15.
 */
public class AnswerQuestions extends Activity {

    Dream dream;

    ImageButton Save, Cancel;
    EditText q1, q2, q3;
    public User u;
    SharedPreferences sprefs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_questions_layout);
        setTitle("Answer Questions About Your Dream");

        Bundle extras = getIntent().getExtras();
        dream = (Dream) extras.getSerializable("dream");

        Save = (ImageButton) findViewById(R.id.activity_questions_save_button);
            Cancel = (ImageButton) findViewById(R.id.activity_questions_cancel_button);

        q1 = (EditText) findViewById(R.id.QuestionEdittext1);
        q2 = (EditText) findViewById(R.id.QuestionEdittext2);
        q3 = (EditText) findViewById(R.id.QuestionEdittext3);

        sprefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());

        u = (User) getIntent().getSerializableExtra("user");

        // Read in previously saved answer to question 1
        try {
            if(dream.getQ1Answer() == "Did not answer") {
                q1.setText(" ");
            }

            else { q1.setText(dream.getQ1Answer()); }
        }

        catch (NullPointerException e) {

        }

        // Read in previously saved answer to question 2
        try {
            if(dream.getQ2Answer() == "Did not answer") {
                q2.setText(" ");
            }

            else { q2.setText(dream.getQ1Answer()); }
        }

        catch (NullPointerException e) {

        }

        // Read in previously saved answer to question 3
        try {
            if(dream.getQ3Answer() == "Did not answer") {
                q3.setText(" ");
            }

            else { q3.setText(dream.getQ1Answer()); }
        }

        catch (NullPointerException e) {

        }

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(AnswerQuestions.this, Home.class);
                startActivity(go);
            }
        });

        Save.setEnabled(true);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(AnswerQuestions.this, Home.class);

                if(q1.getText().toString() == " ") {
                    dream.setQ1Answer("Did not answer");
                }

                else { dream.setQ1Answer(q1.getText().toString()); }

                if(q2.getText().toString() == " ") {
                    dream.setQ2Answer("Did not answer");
                }

                else { dream.setQ2Answer(q1.getText().toString()); }

                if(q3.getText().toString() == " ") {
                    dream.setQ3Answer("Did not answer");
                }

                else { dream.setQ3Answer(q1.getText().toString()); }
                
                u.addDream(dream);

                SharedPreferences.Editor prefsEditor = sprefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(u);
                prefsEditor.putString("MyObject", json);
                prefsEditor.commit();

                startActivity(go);
            }
        });


    }

    public void onBackPressed() {

        Intent go = new Intent(AnswerQuestions.this, OtherDreamInfo.class);
        if(q1.getText().toString() == " ") {
            dream.setQ1Answer("Did not answer");
        }

        else { dream.setQ1Answer(q1.getText().toString()); }

        if(q2.getText().toString() == " ") {
            dream.setQ2Answer("Did not answer");
        }

        else { dream.setQ2Answer(q1.getText().toString()); }

        if(q3.getText().toString() == " ") {
            dream.setQ3Answer("Did not answer");
        }

        else { dream.setQ3Answer(q1.getText().toString()); }

        go.putExtra("dream", dream);
        go.putExtra("user", u);
        startActivity(go);
    }
}
