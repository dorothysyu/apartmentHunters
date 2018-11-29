package hciadk.apartmenthunters.settings;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

import hciadk.apartmenthunters.R;

public class AddRoommatesActivity extends AppCompatActivity {

    String lineSep = System.getProperty("line.separator");
    private final String[] USERS = new String[] {
            "@jackchen | (617)439-1039 | jackiechen@gmail.com",
            "@james101 | (617)325-1294 | jamesrocketpower@gmail.com",
            "@katieleesmith | (617)542-4321 | kls@gmail.com"
    };

    private final String[] USERNAMES = new String[] {
            "@jackchen",
            "@james101",
            "@katieleesmith"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_roommates_activity);

        Button skipBtn = findViewById(R.id.skip_add_roommates);
        Button continueBtn = findViewById(R.id.add_roommate);
        Button addRoommate = findViewById(R.id.add_feature);
        final LinearLayout ll = findViewById(R.id.linearLayout2);
        final AutoCompleteTextView t = findViewById(R.id.type_user);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddRoommatesActivity.this,
                        FeaturesActivity.class));
            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddRoommatesActivity.this,
                        FeaturesActivity.class));
            }
        });

        addRoommate.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          String content = t.getText().toString(); //gets you the contents of edit text
                                          final TextView textView = new TextView(getApplicationContext());
                                          textView.setText(content);
                                          ll.addView(textView);
                                          final Button btn = new Button(getApplicationContext());
                                          btn.setText("remove");
                                          btn.setTextColor(Color.parseColor("#33458C"));
                                          btn.setBackgroundColor(Color.WHITE);
                                          btn.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  ll.removeView(textView);
                                                  ll.removeView(btn);
                                              }
                                          });
                                          ll.addView(btn);
                                          t.setText("");
                                      }
                                  }
        );


        String MY_PREFS_NAME = "blue";

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("name", "Elena");
        editor.putInt("idName", 12);
        editor.apply();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, USERS);
        final AutoCompleteTextView textView = findViewById(R.id.type_user);
        textView.setAdapter(adapter);

        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                int pos = Arrays.asList(USERS).indexOf(selected);

                Log.d("list item", selected);

                textView.setText(Arrays.asList(USERNAMES).get(pos));
            }
        });
    }
}
