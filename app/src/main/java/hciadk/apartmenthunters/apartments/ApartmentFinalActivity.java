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

        LoadPreferences();

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

    private void LoadPreferences(){
        String name = "aptInfo";
        SharedPreferences sharedPreferences = getSharedPreferences(name, MODE_PRIVATE);
        LinearLayout ll = findViewById(R.id.criteria_list);
        LinearLayout extraFeatureLayout = findViewById(R.id.added_feature_list);

        //necessary criteria checklist handling
        int childCount = ll.getChildCount();
        boolean[] criteria = new boolean[childCount];

        for(int i = 0; i < childCount; i++) {
            Boolean boo = sharedPreferences.getBoolean("checked" + i, false);
            Log.d("call sharedpref", boo + "");
            criteria[i] = boo;
        }

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
