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

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

import hciadk.apartmenthunters.R;

public class ApartmentEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_edit);

        Button extraNotesBtn = findViewById(R.id.extra_notes);
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
        LoadPreferences();

        extraNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ApartmentEditActivity.this,
                        ExtraNotesActivity.class));
                finish();
            }
        });

        Button addPhotos = findViewById(R.id.prompt_photos);

        addPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ApartmentEditActivity.this,
                        PickPhotoActivity.class));
                finish();
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

    public View[] getContentsOfChecklist(LinearLayout myLinearLayout, int childCount) {
        // create array
        View[] children = new View[childCount];
        // get children of linearLayout
        for (int i=0; i < childCount; i++){
            children[i] = myLinearLayout.getChildAt(i);
        }

        return children;
    }


    public void reorder() {

        LinearLayout myLinearLayout = findViewById(R.id.linearLayout2);
        int childCount = myLinearLayout.getChildCount();

        View[] children = getContentsOfChecklist(myLinearLayout, childCount);

        //now remove all children
        myLinearLayout.removeAllViews();

        for (int i=0; i < childCount - 2; i++) {
            myLinearLayout.addView(children[i]);
        }

        myLinearLayout.addView(children[childCount - 1]);
        myLinearLayout.addView(children[childCount - 2]);
    }

    public void getAdditionalFeatures() {



    }

    public boolean[] getCheckedFeatures() {

        LinearLayout myLinearLayout = findViewById(R.id.criteria_list);
        // get number of children
        int childCount = myLinearLayout.getChildCount();
        View[] children = getContentsOfChecklist(myLinearLayout, childCount);
//        ArrayList<View> views = new ArrayList<View>();

        boolean[] isChecked = new boolean[childCount];

        int i = 0;
        for(View child:children) {
//            views.add(child);
            Log.d("view text", child.getId() + "");

            if(child instanceof CheckBox) {
                CheckBox box = (CheckBox) child;
                boolean bool = box.isChecked();
                isChecked[i] = bool;
                Log.d("is feat checked", bool + "");
                i += 1;
            }
        }

        return isChecked;
    }

    public void recheckCheckboxes() {
        //LinearLayout ll =
    }

    private void SavePreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putBoolean("state", button.isEnabled());

        boolean[] checkedCriteria = getCheckedFeatures();

        int i = 0;
        for (boolean boo:checkedCriteria) {
            editor.putBoolean("checked" + i, boo);
            Log.d("savepref", boo + "");
            i+=1;
        }

        editor.commit();
        Log.d("savepreferences", "hello");
        // I missed to save the data to preference here,.
    }

    private void LoadPreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
//        Boolean  state = sharedPreferences.getBoolean("state", false);
//        button.setEnabled(state);
//        Log

        LinearLayout ll = findViewById(R.id.criteria_list);
        int childCount = ll.getChildCount();
        boolean[] criteria = new boolean[childCount];
//
//        Log.d("crit size", criteria.length + "");
//        Boolean boo = sharedPreferences.getBoolean("checked0", false);
//        Log.d("sharedpref working", boo + "");

        for(int i = 0; i < childCount; i++) {
            Boolean boo = sharedPreferences.getBoolean("checked" + i, false);
            Log.d("call sharedpref", boo + "");
            criteria[i] = boo;
        }
//
////        Log.d("checked Criteria", Boolean.toString(criteria[1]) + criteria[2] + "");
//
        View[] checkboxes = getContentsOfChecklist(ll, childCount);
        int j = 0;
        for (View child:checkboxes) {
            CheckBox box = (CheckBox) child;
            box.setChecked(criteria[j]);
            j++;
        }

    }

    @Override
    public void onBackPressed() {
        SavePreferences();
        super.onBackPressed();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
//        // killed and restarted.
//        savedInstanceState.putBoolean("MyBoolean", true);
//        savedInstanceState.putDouble("myDouble", 1.9);
//        savedInstanceState.putInt("MyInt", 1);
//        savedInstanceState.putString("MyString", "Welcome back to Android");

        savedInstanceState.putBooleanArray("checkedCriteria", getCheckedFeatures());
        Log.d("savedinstancestate", "hello");

//        savedInstanceState.putSerializable("Criteria", getCheckedFeatures());
        // etc.
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
//        // This bundle has also been passed to onCreate.
//        boolean myBoolean = savedInstanceState.getBoolean("MyBoolean");
//        double myDouble = savedInstanceState.getDouble("myDouble");
//        int myInt = savedInstanceState.getInt("MyInt");
//        String myString = savedInstanceState.getString("MyString");

        boolean[] checkedCriteria = savedInstanceState.getBooleanArray("checkedCriteria");
        Log.d("checked Criteria", Boolean.toString(checkedCriteria[1]) + checkedCriteria[2] + "");
//        Serializable criteria = savedInstanceState.getSerializable("Criteria");
    }
}
