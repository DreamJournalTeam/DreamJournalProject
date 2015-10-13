package md.dreamdiary;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;


public class Home extends Activity {

    private Button recordButton, searchButton;
    private EditText welcome;

    public User u;
    SharedPreferences sprefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Lucid Dream Diary Application");

        // Retrieve our user
        sprefs = getPreferences(MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sprefs.getString("u", "");
        u = gson.fromJson(json, User.class);
        if(u == null) {
            u = new User();
            u.hasLoggedIn();
        }

        recordButton = (Button) findViewById(R.id.home_record_dream_button);
        recordButton.setEnabled(true);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(Home.this, RecordText.class);
                go.putExtra("user", u);
                startActivity(go);
            }
        });

        searchButton = (Button) findViewById(R.id.home_see_log_button);
        searchButton.setEnabled(true);

    }



}
