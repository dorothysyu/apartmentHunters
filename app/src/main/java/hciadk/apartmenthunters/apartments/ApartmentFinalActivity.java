package hciadk.apartmenthunters.apartments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import hciadk.apartmenthunters.R;

public class ApartmentFinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_final);

     //   final LinearLayout ll = findViewById(R.id.linearLayout2);

        getNecessaryCriteria();
        LoadPreferences();

    }



    public void getNecessaryCriteria() {
        final LinearLayout checklist = findViewById(R.id.criteria_list_final);
        String MY_PREFS_NAME = "featureList";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int size = prefs.getInt("size", 0);

        String[] features = new String[size];
        String feature;

        for(int i = 0; i < size; i++) {
            feature = prefs.getString("feature" + i, "No feature defined");
            features[i] = feature;
        }

        restoreChecklist(checklist, features);
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

    private void LoadPreferences(){
        String name = "aptInfo";
        SharedPreferences sharedPreferences = getSharedPreferences(name, MODE_PRIVATE);
        LinearLayout ll = findViewById(R.id.criteria_list_final);
        LinearLayout extraFeatureLayout = findViewById(R.id.added_feature_list);

        //necessary criteria checklist handling
        int childCount = ll.getChildCount();


        //extra feature checklist handling

        int extraFeatListSize = sharedPreferences.getInt("extraFeatSize", 0);
        boolean[] extraCriteria = new boolean[extraFeatListSize];

        String[] extraFeatures = new String[extraFeatListSize];
        String feature;


        for(int i = 0; i < extraFeatListSize; i++) {
            feature = sharedPreferences.getString("extraFeat" + i, "No feature defined");
            extraFeatures[i] = feature;
        }

        int extraFeatSize = extraFeatureLayout.getChildCount();

        for(int i = 0; i < extraFeatSize; i++) {
            Boolean boo = sharedPreferences.getBoolean("extraChecked" + i, false);
            extraCriteria[i] = boo;
        }

        //set price
        int price = sharedPreferences.getInt("price", 0);
        TextInputEditText priceField = findViewById(R.id.price_text_edit);
        if (price != 0) {
            priceField.setText(String.valueOf(price));
        }
    }
}
