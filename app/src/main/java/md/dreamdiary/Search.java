package md.dreamdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    ImageButton search;
    EditText textfield;
    Spinner spinner;
    ListView lv;
    User u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_search);

        search = (ImageButton) findViewById(R.id.search_screen_search_button);

        textfield = (EditText) findViewById(R.id.search_term_edittext);

        spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnerItems, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        u = (User) getIntent().getSerializableExtra("user");
        final ArrayList<Dream> theDreams = u.getList();

        final ArrayList<Dream> results = new ArrayList<Dream>();
        final ArrayList<Integer> indices = new ArrayList();


        // Set our on click listener for the search button.
        // What it does depends on what was selected in the spinner.
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                results.clear();
                indices.clear();

                String term = textfield.getText().toString();

                Object selected = spinner.getSelectedItem();

                // Search by title
                if (selected == spinner.getItemAtPosition(0)) {
                    for (int i = 0; i < theDreams.size(); i++) {
                        if (theDreams.get(i).getTitle().contains(term)) {
                            results.add(theDreams.get(i));
                            indices.add(i);
                        }
                    }
                // Search by date
                } else if (selected == spinner.getItemAtPosition(1)) {

                    for (int i = 0; i < theDreams.size(); i++) {
                        if (theDreams.get(i).getDate().contains(term)) {
                            results.add(theDreams.get(i));
                        }
                    }
                // Search by tags
                } else if (selected == spinner.getItemAtPosition(2)) {

                    for (int i = 0; i < theDreams.size(); i++) {
                        if (theDreams.get(i).getTags().contains(term)) {
                            results.add(theDreams.get(i));
                        }
                    }
                // Search by nouns
                } else if (selected == spinner.getItemAtPosition(3)) {

                    for (int i = 0; i < theDreams.size(); i++) {
                        if (theDreams.get(i).getNouns().contains(term)) {
                            results.add(theDreams.get(i));
                        }
                    }

                }

                System.out.println(results.size() + " is the size of results");


                DreamAdapter d = new DreamAdapter(
                        getApplicationContext(),
                        R.layout.list_item,
                        results);

                lv = (ListView) findViewById(R.id.dreamsListView);
                lv.setAdapter(d);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(final AdapterView<?> adapter, View v, final int position,
                                            long arg3) {

                        Dream d = (Dream) adapter.getItemAtPosition(position);
                        int theIndex = indices.get(position);
                        Intent go = new Intent(Search.this, EditDream.class);
                        go.putExtra("user", u);
                        go.putExtra("dream", (Dream) adapter.getItemAtPosition(position));
                        go.putExtra("index", theIndex);
                        startActivity(go);

                    }
                });
            }

        });
    }

}
