package md.dreamdiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import java.util.ArrayList;

public class ChangeEnding extends AppCompatActivity {

    Button change;
    EditText text;
    public User u;
    Dream dream;
    SharedPreferences sprefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_ending_screen);


        dream = (Dream) getIntent().getSerializableExtra("dream");
        u = (User) getIntent().getSerializableExtra("user");
        sprefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());

        System.out.println(u == null);
        ArrayList<Dream> testing = u.getList();
        System.out.println(testing.size());


        text = (EditText) findViewById(R.id.change_ending_edittext);

        try {
            text.setText(dream.getText());
        } catch (NullPointerException e) {

        }

        change = (Button) findViewById(R.id.change_ending_save_button);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dream.setText(text.getText().toString());
                Intent go = new Intent(ChangeEnding.this, OtherDreamInfo.class);
                go.putExtra("dream", dream);
                go.putExtra("user", u);
                startActivity(go);
            }
        });
    }

}
