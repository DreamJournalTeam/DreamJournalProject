package md.dreamdiary;

/**
 * Created by Matthew on 11/18/15.
 */

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.view.*;
import java.util.ArrayList;

import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.content.Intent;

public class DreamAdapter extends ArrayAdapter<Dream> {

    private  ArrayList<Dream> list;

    public DreamAdapter(Context context, int resource, ArrayList<Dream> list) {
        super(context, resource, list);
        this.list = list;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, null);
        }


        Dream i = list.get(position);

        if (i != null) {

            TextView tt = (TextView) v.findViewById(R.id.titletext);
            TextView ttd = (TextView) v.findViewById(R.id.titletextdata);
            TextView dt = (TextView) v.findViewById(R.id.datetext);
            TextView dtd = (TextView) v.findViewById(R.id.datetextdata);


            if (tt != null) {
                tt.setText("Name: ");
            }
            if (ttd != null) {
                ttd.setText(i.getTitle());
            }
            if (dt != null) {
                dt.setText("Date: ");
            }
            if (dtd != null) {
                dtd.setText(i.getDate());
            }



        }


        return v;



    }
}
