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

        Save.setEnabled(true);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(AnswerQuestions.this, OtherDreamInfo.class);
                startActivity(go);
            }
        });

        Cancel.setEnabled(true);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnswerQuestions.this, OtherDreamInfo.class));
            }
        });
    }
}
