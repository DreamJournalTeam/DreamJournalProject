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
 * Created by michaeldiamond on 9/14/15.
 */
public class RecordText extends Activity {

    // Our dream so far
    private Dream dream;

    // What we need for this activity
    private Button Save, changeEnding, continue1;
    private EditText dreamText;

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_record_text);
        setTitle("Record your dream");

        try {
            // Bring in our saved information
            dream = (Dream) getIntent().getSerializableExtra("dream");
        }

        catch (NullPointerException e) {
            dream = new Dream();
        }


        // Hit this button to save your dream
        continue1 = (Button) findViewById(R.id.enter_dream_continue_button);

        changeEnding = (Button) findViewById(R.id.enter_dream_change_ending_button);

        // What stores the text of the dream
        dreamText = (EditText) findViewById(R.id.enter_dream_edittext);

        continue1.setEnabled(true);
        continue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(RecordText.this, OtherDreamInfo.class);

                if (!(dream.getText() == null)) {
                    dream.setText(dreamText.getText().toString());
                }

                go.putExtra("dream", dream);
                startActivity(go);
            }
        });

        // Do things for the other buttons. Right now I have no plan for them
    }

}
