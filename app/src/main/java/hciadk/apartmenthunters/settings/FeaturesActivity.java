package hciadk.apartmenthunters.settings;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import hciadk.apartmenthunters.apartments.MainActivity;
import hciadk.apartmenthunters.R;

public class FeaturesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);

        Button doneBtn = findViewById(R.id.continue_from_criteria);
        Button backBtn = findViewById(R.id.back_to_ind_apt);
        final Button addBtn = findViewById(R.id.add_feature);
        final LinearLayout ll = findViewById(R.id.linearLayout2);
        final TextInputEditText t = findViewById(R.id.prompt_add_own_feature);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FeaturesActivity.this,
                        MainActivity.class));
            }
        });

/*        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FeaturesActivity.this,
                        AddRoommatesActivity.class));
            }
        });*/

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
//
//        myLinearLayout.addView(children[childCount]);
        myLinearLayout.addView(children[childCount-1]);
        myLinearLayout.addView(children[childCount-2]);

//        //and resort, first position
//        myLinearLayout.addView(children[2]);
//        //second position
//        myLinearLayout.addView(children[0]);
//        //etc.
    }
}
