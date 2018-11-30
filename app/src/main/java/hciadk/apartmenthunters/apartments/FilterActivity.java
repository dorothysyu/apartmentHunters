package hciadk.apartmenthunters.apartments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;

import hciadk.apartmenthunters.R;

public class FilterActivity extends AppCompatActivity {

    ArrayList<String> filters = new ArrayList<>();
    int aptNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        Button filterBtn = findViewById(R.id.filter_btn);

        getApt();
        getFilters();

        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FilterActivity.this,
                        AllApartmentsActivity.class));
                finish();
            }
        });
    }
    public int getApt() {
        String MY_PREFS_NAME = "whichApt";

        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        aptNum = sharedPreferences.getInt("apt", 0);
        return aptNum;
    }

    public void restoreChecklist(LinearLayout myLinearLayout, ArrayList<String> features) {
        CheckBox newBox;
        for(String feat: features) {
            newBox = new CheckBox(getApplicationContext());
            newBox.setText(feat);
            myLinearLayout.addView(newBox);
//            Log.d("all features", feat);
        }

    }

    public void getFilters() {
        final LinearLayout checklist = findViewById(R.id.filters_list);
        String MY_PREFS_NAME = "featureList";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int size = prefs.getInt("size", 0);

        String[] features = new String[size];
        String feature;

        for(int i = 0; i < size; i++) {
            feature = prefs.getString("feature" + i, "No feature defined");
//            features[i] = feature;
            filters.add(feature);
        }

//        restoreChecklist(checklist, features);

        //extra features list

        MY_PREFS_NAME = "aptInfo";
        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        int extraFeatListSize = prefs.getInt("extraFeatSize", 0);

        String[] extraFeatures = new String[extraFeatListSize];
        String extraFeature;

        Log.d("filters extraFeatSize", extraFeatListSize + "");


        for(int i = 0; i < extraFeatListSize; i++) {
            extraFeature = prefs.getString(
//                    "aptNum" + aptNum + " extraFeat" + i, "No feature defined");
                    "extraFeat" + i, "No feature defined");
//            extraFeatures[i] = extraFeature;
            filters.add(extraFeature);
        }

        restoreChecklist(checklist, filters);
    }


}
