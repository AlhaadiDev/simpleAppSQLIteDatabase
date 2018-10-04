package com.example.simpleappsqlitedatabase;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class PlacesList extends AppCompatActivity {

    DBController controller = new DBController(this);
    ListView ls;
    TextView infotext;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.placeslist);

        ls = (ListView) findViewById(R.id.placeslist);
        infotext = (TextView) findViewById(R.id.txtresulttext);

        try {
            List<HashMap<String, String>> data = controller.getAllPlace();
            if (data.size() != 0) {
                Toast.makeText(PlacesList.this, "YEs data", Toast.LENGTH_SHORT).show();
                // Srno, RMCode, Fileno, Loc, FileDesc, TAGNos
                SimpleAdapter adapter = new SimpleAdapter(
                        PlacesList.this, data, R.layout.rows,
                new String[]{"id", "place", "country"}, new int[]{
                        R.id.txtplaceid, R.id.txtplacename,
                        R.id.txtcountry});

                ls.setAdapter(adapter);
                String length = String.valueOf(data.size());
                infotext.setText(length + " places");
            } else {
                infotext.setText("No data in database");
            }

        } catch (Exception ex) {
            infotext.setText(ex.getMessage().toString());
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


}
