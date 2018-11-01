package hciadk.apartmenthunters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class AddRoommatesActivity extends AppCompatActivity {

    String lineSep = System.getProperty("line.separator");
    private final String[] USERS = new String[] {
            "@jackchen | (617)439-1039 | jackiechen@gmail.com",
            "@james101 | (617)325-1294 | jamesrocketpower@gmail.com"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_roommates_activity);

        Button skipBtn = findViewById(R.id.skip_add_roommates);
        Button continueBtn = findViewById(R.id.add_roommate);
        Button addViaPhoneBtn = findViewById(R.id.add_via_phone);
        Button addViaEmailBtn = findViewById(R.id.add_via_email);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddRoommatesActivity.this,
                        FeaturesActivity.class));
            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddRoommatesActivity.this,
                        FeaturesActivity.class));
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, USERS);
        AutoCompleteTextView textView = findViewById(R.id.type_user);
        textView.setAdapter(adapter);
    }
}
