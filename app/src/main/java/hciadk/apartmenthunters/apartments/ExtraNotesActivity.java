package hciadk.apartmenthunters.apartments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hciadk.apartmenthunters.R;

public class ExtraNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_notes);


        Button extraNotesBtn = findViewById(R.id.save_extra_notes);
        final EditText extra = findViewById(R.id.editText2);

        loadPreferences();

        extraNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String notes = extra.getText().toString();
                savePreferences(notes);

                startActivity(new Intent(ExtraNotesActivity.this,
                        ApartmentEditActivity.class));
            }
        });
    }

    private void savePreferences(String notes) {

        String MY_PREFS_NAME = "extraNotes";
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(MY_PREFS_NAME, notes);
        editor.apply();
    }

    private void loadPreferences() {

        String MY_PREFS_NAME = "extraNotes";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        String notes = prefs.getString(MY_PREFS_NAME, "");

        final EditText extra = findViewById(R.id.editText2);
        extra.setText(notes);

    }
}
