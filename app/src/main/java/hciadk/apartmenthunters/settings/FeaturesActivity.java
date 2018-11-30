package hciadk.apartmenthunters.settings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import hciadk.apartmenthunters.apartments.AllApartmentsActivity;
import hciadk.apartmenthunters.apartments.MainActivity;
import hciadk.apartmenthunters.R;

public class FeaturesActivity extends AppCompatActivity {

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String BG_SELECT = "hello";
            String recievedString = intent.getStringExtra(BG_SELECT);
            Log.d("this is the intent", recievedString);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);


        Button doneBtn = findViewById(R.id.continue_from_criteria);
        final Button addBtn = findViewById(R.id.add_feature);
        final LinearLayout ll = findViewById(R.id.linearLayout2);
        final TextInputEditText t = findViewById(R.id.prompt_add_own_feature);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ArrayList features = getWantedFeatures();
//                Log.d("print wanted feature", (String) features.get(0));

                TextInputEditText priceField = findViewById(R.id.prompt_price);
                if(priceField.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Need to input price!", Toast.LENGTH_LONG)
                            .show();
                } else {
                    getWantedFeatures();
                    startActivity(new Intent(FeaturesActivity.this,
                            AllApartmentsActivity.class));
                }
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = t.getText().toString(); //gets you the contents of edit text
                CheckBox cb = new CheckBox(getApplicationContext());
                cb.setText(content);
                cb.setChecked(true);
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

        for (int i=0; i<childCount - 2; i++) {
            myLinearLayout.addView(children[i]);
        }
        myLinearLayout.addView(children[childCount-1]);
        myLinearLayout.addView(children[childCount-2]);
    }

    public void getWantedFeatures() {
        String MY_PREFS_NAME = "featureList";

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        LinearLayout myLinearLayout = findViewById(R.id.linearLayout2);
        // get number of children
        int childCount = myLinearLayout.getChildCount();
        // create array
        View[] children = new View[childCount];
        // get children of linearlayout
        for (int i=0; i < childCount; i++){
            children[i] = myLinearLayout.getChildAt(i);
        }

        int i = 0;
        for(View child:children) {
            if(child instanceof CheckBox) {
                CheckBox value = (CheckBox) child;
                if (value.isChecked()) {
                    String content = value.getText().toString();
                    editor.putString("feature" + i, content);
                    i += 1;
                }
            }
        }

        editor.putInt("size", i);

        TextInputEditText priceField = findViewById(R.id.prompt_price);
        int price = Integer.valueOf(priceField.getText().toString());
        editor.putInt("maxPrice", price);


        editor.apply();
//        return features;
    }
}
