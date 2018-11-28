package hciadk.apartmenthunters.apartments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

import hciadk.apartmenthunters.R;

public class ApartmentEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_edit);

        Button extraNotesBtn = findViewById(R.id.extra_notes);
     //   ImageButton homeBtn = findViewById(R.id.home);
        final Button addBtn = findViewById(R.id.add_feature);
        final LinearLayout ll = findViewById(R.id.linearLayout2);
        final TextInputEditText t = findViewById(R.id.prompt_add_own_feature);
        final LinearLayout checklist = findViewById(R.id.criteria_list);

        String MY_PREFS_NAME = "featureList";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int size = prefs.getInt("size", 0);

        ArrayList<String> features = new ArrayList();
        String feature;

        for(int i = 0; i < size; i++) {
            feature = prefs.getString("feature" + i, "No feature defined");
            features.add(feature);
        }

        CheckBox newBox;

        for(String feat: features) {
            newBox = new CheckBox(getApplicationContext());
            newBox.setText(feat);
            checklist.addView(newBox);
            Log.d("all features", feat);
        }

        extraNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ApartmentEditActivity.this,
                        ExtraNotesActivity.class));
            }
        });

/*        Button features = findViewById(R.id.prompt_match);

        features.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ApartmentEditActivity.this,
                        IndividualChecklistActivity.class));
            }
        });*/


/*        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ApartmentEditActivity.this,
                        AllApartmentsActivity.class));
            }
        });*/

/*        Button backToMain = findViewById(R.id.back_to_main);

        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ApartmentEditActivity.this,
                        MainActivity.class));
            }
        });*/

        Button addPhotos = findViewById(R.id.prompt_photos);

        addPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ApartmentEditActivity.this,
                        PickPhotoActivity.class));
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          String content = t.getText().toString(); //gets you the contents of edit text
                                          CheckBox cb = new CheckBox(getApplicationContext());
                                          cb.setChecked(true);
                                          cb.setText(content);
                                          ll.addView(cb);
                                          reorder();
                                          t.setText("");
                                      }
                                  }

        );



    }

    public void reorder() {

        LinearLayout myLinearLayout = findViewById(R.id.linearLayout2);
        // get number of children
        int childCount = myLinearLayout.getChildCount();
        // create array
        View[] children = new View[childCount];

        // get children of linearlayout
        for (int i=0; i < childCount; i++){
            children[i] = myLinearLayout.getChildAt(i);
        }

        //now remove all children
        myLinearLayout.removeAllViews();

        for (int i=0; i < childCount - 2; i++) {
            myLinearLayout.addView(children[i]);
        }

        myLinearLayout.addView(children[childCount - 1]);
        myLinearLayout.addView(children[childCount - 2]);
    }
}
