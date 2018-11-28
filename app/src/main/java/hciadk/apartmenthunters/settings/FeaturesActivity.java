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
//
//        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver,
//                new IntentFilter("INTENT_NAME"));

//        Intent intent = getIntent();
//        String easyPuzzle = intent.getExtras().getString("epuzzle");
//        Log.d("hello", easyPuzzle);

//        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter("INTENT_NAME"));

//        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
//        String m = bb.getString("NUM", "");
//        Toast.makeText(this, m, Toast.LENGTH_SHORT).show();
//        Log.d("hello", m)
//
//        String MY_PREFS_NAME = "blue";
//        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//        String restoredText = prefs.getString("text", null);
//        String name = prefs.getString("name", "No name defined");
//        if (restoredText != null) {
//            //String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
//            int idName = prefs.getInt("idName", 0); //0 is the default value.
//            Log.d("intent works", name);
//            Log.d("intent still works", idName + "");
//        }
//
//
//        Log.d("don't crash", "hi");
//        Log.d("you'll crash tho", name);


        Button doneBtn = findViewById(R.id.continue_from_criteria);
        final Button addBtn = findViewById(R.id.add_feature);
        final LinearLayout ll = findViewById(R.id.linearLayout2);
        final TextInputEditText t = findViewById(R.id.prompt_add_own_feature);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ArrayList features = getWantedFeatures();
//                Log.d("print wanted feature", (String) features.get(0));
                getWantedFeatures();
                startActivity(new Intent(FeaturesActivity.this,
                        AllApartmentsActivity.class));
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

//        ArrayList features = new ArrayList();
//
//        for(View child:children) {
//            if(child instanceof CheckBox) {
//                CheckBox value = (CheckBox) child;
//                if (value.isChecked()) {
//                    String content = value.getText().toString();
//                    features.add(content);
//                }
//            }
//        }
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

        editor.apply();
//        return features;
    }
}
