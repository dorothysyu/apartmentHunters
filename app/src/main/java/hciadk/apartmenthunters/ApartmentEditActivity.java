package hciadk.apartmenthunters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ApartmentEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_edit);

        Button extraNotesBtn = findViewById(R.id.extra_notes);

        extraNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ApartmentEditActivity.this,
                        ExtraNotesActivity.class));
            }
        });

        Button features = findViewById(R.id.prompt_match);

        features.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ApartmentEditActivity.this,
                        IndividualChecklistActivity.class));
            }
        });

    }
}
