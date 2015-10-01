package md.dreamdiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Home extends Activity {

    private Button recordButton, searchButton;
    private EditText welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Lucid Dream Diary Application");

        recordButton = (Button) findViewById(R.id.home_record_dream_button);
        recordButton.setEnabled(true);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, RecordText.class));
            }
        });

        searchButton = (Button) findViewById(R.id.home_see_log_button);
        searchButton.setEnabled(true);

    }



}
