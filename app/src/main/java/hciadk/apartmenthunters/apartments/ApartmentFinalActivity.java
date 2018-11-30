package hciadk.apartmenthunters.apartments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import hciadk.apartmenthunters.R;

public class ApartmentFinalActivity extends AppCompatActivity {

    int aptNum;
    int maxPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_final);
        getApt();

        getNecessaryCriteria();
        LoadPreferences();
    }

    public int getApt() {
        String MY_PREFS_NAME = "whichApt";

        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        aptNum = sharedPreferences.getInt("apt", 0);
        return aptNum;
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

        maxPrice = prefs.getInt("maxPrice", 10000000);

        restoreChecklist(checklist, features);
    }

    public void restoreChecklist(LinearLayout myLinearLayout, String[] features) {
        int j = 1;
        for(String feat: features) {
            TextView featureTextView = new TextView(getApplicationContext());
            featureTextView.setText(feat);
            myLinearLayout.addView(featureTextView);
            Log.d("all features", feat);
            j++;
        }
    }

    public void takeOutUnmatchedCriteria(LinearLayout ll, View[] checkboxes, boolean[] features) {
        //LinearLayout ll =
//        LinearLayout ll = findViewById(R.id.criteria_list_final);

        int j = 0;
        for (View child:checkboxes) {
            if((!features[j])){
                ll.removeView(child);
            }
            j++;
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

    private void LoadPreferences(){
        String name = "aptInfo";
        SharedPreferences sharedPreferences = getSharedPreferences(name, MODE_PRIVATE);
        LinearLayout ll = findViewById(R.id.criteria_list_final);

        //necessary criteria checklist handling

        int childCount = ll.getChildCount();
        boolean[] criteria = new boolean[childCount];

        for(int i = 0; i < childCount; i++) {
            Boolean boo = sharedPreferences.getBoolean(
                    "aptNum" + aptNum + " checked" + i, false);
            criteria[i] = boo;
        }

        View[] checkboxes = getContentsOfChecklist(ll, childCount);
        takeOutUnmatchedCriteria(ll, checkboxes, criteria);
        //extra feature checklist handling

        LinearLayout extraFeatLayout = findViewById(R.id.added_feature_list);

        //size of the list of extra features
        int extraFeatListSize = sharedPreferences.getInt("extraFeatSize", 0);

        //if the criteria was checked off, true. if not, false. INITIALIZED
        boolean[] extraCriteria = new boolean[extraFeatListSize];

        //actual list of extra features INITIALIZED (so empty)
        String[] extraFeatures = new String[extraFeatListSize];

        Log.d("extra feature size", extraFeatListSize+"");

        String feature;

        //populates list of extra features
        for(int i = 0; i < extraFeatListSize; i++) {
            feature = sharedPreferences.getString("extraFeat" + i, "No feature defined");
            extraFeatures[i] = feature;
        }

        for(String extraFeat:extraFeatures) {
            Log.d("Extra features", extraFeat);
        }

        restoreChecklist(extraFeatLayout, extraFeatures);
        //current size of the linearLayout holding the extra features list
        int extraFeatSize = extraFeatLayout.getChildCount();
        Log.d("extraFeatSize", extraFeatSize + "");

        //fills list of booleans that know if criteria was checked off
        for(int i = 0; i < extraFeatSize; i++) {
            Boolean boo = sharedPreferences.getBoolean(
                    "aptNum" + aptNum + " extraChecked" + i, false);
            extraCriteria[i] = boo;
            Log.d("is Checked off", boo + "");
        }

        View[] extraCheckboxes = getContentsOfChecklist(extraFeatLayout, extraFeatLayout.getChildCount());
        Log.d("childcount size", extraFeatLayout.getChildCount() + "");
       takeOutUnmatchedCriteria(extraFeatLayout,extraCheckboxes, extraCriteria);



        //set price
        int price = sharedPreferences.getInt("aptNum" + aptNum + " price", 0);
        TextView priceField = findViewById(R.id.price_field_final);
        String pricePrompt = "Price: $" ;
        Log.d("price", price + "$");
        if (price != 0 && price < maxPrice) {
            priceField.setText(pricePrompt + String.valueOf(price) + "\nWithin your price range!");
        }
        else if (price != 0 && price > maxPrice) {
            priceField.setText(pricePrompt + String.valueOf(price) + "\nHigher than your price range");
        }

        //get extra notes
        TextView extra = findViewById(R.id.extra_notes_final);
        String MY_PREFS_NAME = "extraNotes";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        String notes = prefs.getString(MY_PREFS_NAME+aptNum, "");

        extra.setText(notes);
    }
}
