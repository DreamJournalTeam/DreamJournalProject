package md.dreamdiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by michaeldiamond on 9/17/15.
 */
public class AnswerQuestions extends Activity {

    Dream dream;

    Button Save, Cancel;
    EditText q1, q2, q3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_questions_layout);
        setTitle("Answer Questions About Your Dream");

        Bundle extras = getIntent().getExtras();
        dream = (Dream) extras.getSerializable("dream");

        Save = (Button) findViewById(R.id.activity_questions_save_button);
        Cancel = (Button) findViewById(R.id.activity_questions_cancel_button);

        q1 = (EditText) findViewById(R.id.QuestionEdittext1);
        q2 = (EditText) findViewById(R.id.QuestionEdittext2);
        q3 = (EditText) findViewById(R.id.QuestionEdittext3);

        // Read in previously saved answer to question 1
        try {
            q1.setText(dream.getQ1Answer());
        }

        catch (NullPointerException e) {
            System.out.println("sad");
        }

        // Read in previously saved answer to question 2
        try {
            q2.setText(dream.getQ2Answer());
        }

        catch (NullPointerException e) {

        }

        // Read in previously saved answer to question 3
        try {
            q3.setText(dream.getQ3Answer());
        }

        catch (NullPointerException e) {

        }

        Save.setEnabled(true);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(AnswerQuestions.this, OtherDreamInfo.class);
                dream.setQ1Answer(q1.getText().toString());
                dream.setQ2Answer(q2.getText().toString());
                dream.setQ3Answer(q3.getText().toString());
                go.putExtra("dream", dream);
                startActivity(go);
            }
        });

        Cancel.setEnabled(true);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnswerQuestions.this, Home.class));
            }
        });
    }

    public void onBackPressed() {

        Intent go = new Intent(AnswerQuestions.this, OtherDreamInfo.class);
        dream.setQ1Answer(q1.getText().toString());
        dream.setQ2Answer(q2.getText().toString());
        dream.setQ3Answer(q3.getText().toString());
        go.putExtra("dream", dream);
        startActivity(go);
    }
}
