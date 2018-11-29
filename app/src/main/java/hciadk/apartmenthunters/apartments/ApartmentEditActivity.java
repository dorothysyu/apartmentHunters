package hciadk.apartmenthunters.apartments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
        final LinearLayout extraChecklist = findViewById(R.id.added_feature_list);

        String MY_PREFS_NAME = "featureList";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int size = prefs.getInt("size", 0);

        String[] features = new String[size];
        String feature;

        for(int i = 0; i < size; i++) {
            feature = prefs.getString("feature" + i, "No feature defined");
            features[i] = feature;
//            features.add(feature);
        }

        restoreChecklist(checklist, features);
        LoadPreferences();


        extraNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePreferences();
                startActivity(new Intent(ApartmentEditActivity.this,
                        ExtraNotesActivity.class));
            }
        });

        Button addPhotos = findViewById(R.id.prompt_photos);

        addPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePreferences();
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
                                          extraChecklist.addView(cb);
                                          t.setText("");
                                      }
                                  }

        );



    }

    public void restoreChecklist(LinearLayout myLinearLayout, String[] features) {
        CheckBox newBox;
        for(String feat: features) {
            newBox = new CheckBox(getApplicationContext());
            newBox.setText(feat);
            myLinearLayout.addView(newBox);
            Log.d("all features", feat);
        }

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


    public void getAdditionalFeatures() {



    }

    public boolean[] getCheckedFeatures(LinearLayout myLinearLayout) {

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
        LinearLayout myLinearLayout = findViewById(R.id.criteria_list);
        LinearLayout extraFeatureLayout = findViewById(R.id.added_feature_list);

        int extraFeatSize = extraFeatureLayout.getChildCount();

        boolean[] checkedCriteria = getCheckedFeatures(myLinearLayout);
        View[] extraFeatures = getContentsOfChecklist(extraFeatureLayout, extraFeatSize);
        boolean[] extraCheckedFeatures = getCheckedFeatures(extraFeatureLayout);

        String[] extraFeatList = new String[extraFeatSize];
        int k = 0;

        for(View feature:extraFeatures) {
            if(feature instanceof CheckBox){
                CheckBox box = (CheckBox) feature;
                String content = box.getText().toString();
                editor.putString("extraFeat" + k, content);
                extraFeatList[k] = content;
                k++;
            }
        }

        editor.putInt("extraFeatSize", extraFeatSize);

        int i = 0;
        for (boolean boo:checkedCriteria) {
            editor.putBoolean("checked" + i, boo);
            i+=1;
        }
//        for(boolean boo:extraCheckedFeatures) {
//            editor.putBoolean("extraFeature")
//        }
//

        editor.apply();
        Log.d("savepreferences", "hello");
    }

    private void LoadPreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);

        LinearLayout ll = findViewById(R.id.criteria_list);
        LinearLayout extraFeatureLayout = findViewById(R.id.added_feature_list);
        int childCount = ll.getChildCount();
        boolean[] criteria = new boolean[childCount];

        for(int i = 0; i < childCount; i++) {
            Boolean boo = sharedPreferences.getBoolean("checked" + i, false);
            Log.d("call sharedpref", boo + "");
            criteria[i] = boo;
        }

        View[] checkboxes = getContentsOfChecklist(ll, childCount);
        int j = 0;
        for (View child:checkboxes) {
            CheckBox box = (CheckBox) child;
            box.setChecked(criteria[j]);
            j++;
        }

        int extraFeatListSize = sharedPreferences.getInt("extraFeatSize", 0);

        String[] extraFeatures = new String[extraFeatListSize];
        String feature;

        for(int i = 0; i < extraFeatListSize; i++) {
            feature = sharedPreferences.getString("extraFeat" + i, "No feature defined");
            extraFeatures[i] = feature;
//            features.add(feature);
        }

        restoreChecklist(extraFeatureLayout, extraFeatures);

    }

    @Override
    public void onBackPressed() {
        SavePreferences();
        super.onBackPressed();
    }
}
