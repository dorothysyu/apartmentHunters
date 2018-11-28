package hciadk.apartmenthunters.apartments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import hciadk.apartmenthunters.R;

public class IndividualChecklistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_checklist);

        Button doneBtn = findViewById(R.id.done_adding);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IndividualChecklistActivity.this,
                        ApartmentEditActivity.class));
            }
        });

        ImageButton homeBtn = findViewById(R.id.home);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IndividualChecklistActivity.this,
                        AllApartmentsActivity.class));
            }
        });

        Button backBtn = findViewById(R.id.back_to_ind_apt);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IndividualChecklistActivity.this,
                        ApartmentEditActivity.class));
            }
        });
    }
}
