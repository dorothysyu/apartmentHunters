package hciadk.apartmenthunters.apartments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import hciadk.apartmenthunters.R;

public class ApartmentFinalActivity extends AppCompatActivity {

    int aptNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_final);
        getApt();

     //   final LinearLayout ll = findViewById(R.id.linearLayout2);
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

        restoreChecklist(checklist, features);
    }

    public void restoreChecklist(LinearLayout myLinearLayout, String[] features) {
        TextView featureTextView;
        int j = 1;
        for(String feat: features) {
            featureTextView = new TextView(getApplicationContext());
            featureTextView.setText(j + ". " + feat);
            myLinearLayout.addView(featureTextView);
            Log.d("all features", feat);
            j++;
        }

        final LinearLayout extraFeatLayout = findViewById(R.id.added_feature_list);
        String EXTRA = "extraFeatureList";
        SharedPreferences extraPref = getSharedPreferences(EXTRA, MODE_PRIVATE);
        int size = extraPref.getInt("size", 0);

        String[] extraFeatList = new String[size];
        TextView extraFeatureTextView;
        int i = 0;
        for(String extraFeat : extraFeatList) {
            extraFeat = extraPref.getString("feature" + i, "No feature defined");
            features[i] = feature;
        }
    }

    public void takeOutUnmatchedCriteria(View[] checkboxes, boolean[] features) {
        //LinearLayout ll =
        LinearLayout ll = findViewById(R.id.criteria_list_final);

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
        LinearLayout extraFeatureLayout = findViewById(R.id.added_feature_list);

        //necessary criteria checklist handling

        int childCount = ll.getChildCount();
        boolean[] criteria = new boolean[childCount];

        for(int i = 0; i < childCount; i++) {
            Boolean boo = sharedPreferences.getBoolean(
                    "aptNum" + aptNum + " checked" + i, false);
            criteria[i] = boo;
        }

        View[] checkboxes = getContentsOfChecklist(ll, childCount);
        takeOutUnmatchedCriteria(checkboxes, criteria);
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
