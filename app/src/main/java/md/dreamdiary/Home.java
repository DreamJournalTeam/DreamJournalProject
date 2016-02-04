package md.dreamdiary;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.ListView;
import android.content.SharedPreferences.*;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.google.gson.Gson;

import java.util.ArrayList;


public class Home extends Activity {

    private ImageButton recordButton, searchButton;
    private EditText welcome;

    public User u;
    SharedPreferences sprefs;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Lucid Dream Diary Application");

        // Retrieve our user
        sprefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        Editor prefsEditor = sprefs.edit();
        Gson gson = new Gson();
        String json = sprefs.getString("MyObject", "");
        u = gson.fromJson(json, User.class);

        if(u == null) {
            u = new User();
            u.hasLoggedIn();
        }

        // Load a list of all the user's dreams
        final ArrayList<Dream> theDreams = u.getList();

        // Set the dream adapter
        DreamAdapter d = new DreamAdapter(
                this,
                R.layout.list_item,
                theDreams);

        // Create a listview then show the dreams using the listview and adapter
        lv = (ListView) findViewById(R.id.dreamsListView);
        lv.setAdapter(d);

        // If we click on one of the dreams, we can edit it
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapter, View v, final int position,
                                    long arg3) {

                Dream d = (Dream) adapter.getItemAtPosition(position);
                Intent go = new Intent(Home.this, EditDream.class);
                go.putExtra("user", u);
                go.putExtra("dream", (Dream) adapter.getItemAtPosition(position));
                go.putExtra("index", position);
                startActivity(go);

            }
        });

        // Record button
        recordButton = (ImageButton) findViewById(R.id.home_record_dream_button);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(Home.this, RecordText.class);
                go.putExtra("user", u);
                startActivity(go);
            }
        });

        // Search button
        searchButton = (ImageButton) findViewById(R.id.home_search_log_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(Home.this, Search.class);
                go.putExtra("user", u);
                startActivity(go);

            }
        });


    }



}
