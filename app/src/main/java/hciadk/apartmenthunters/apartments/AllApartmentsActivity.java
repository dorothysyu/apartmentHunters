package hciadk.apartmenthunters.apartments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.widget.TextView;

import hciadk.apartmenthunters.R;
import hciadk.apartmenthunters.settings.AddRoommatesActivity;
import hciadk.apartmenthunters.settings.FeaturesActivity;

public class AllApartmentsActivity extends AppCompatActivity {

    @Override
    protected void onStart(){

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_apts);

        final TableRow aptRow1 = findViewById(R.id.apt1);
        final TableRow aptRow2 = findViewById(R.id.apt2);
        final TableRow aptRow3 = findViewById(R.id.apt3);
        ImageButton add = findViewById(R.id.add_new_apt);

        aptRow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllApartmentsActivity.this,
                        ApartmentEditActivity.class));
            }
        });

        aptRow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllApartmentsActivity.this,
                        ApartmentEditActivity.class));
            }
        });

        aptRow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllApartmentsActivity.this,
                        ApartmentEditActivity.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllApartmentsActivity.this,
                        AddApartmentActivity.class));
            }
        });

        //aptRow1.setVisibility(View.INVISIBLE);
        aptRow2.setVisibility(View.INVISIBLE);
        aptRow3.setVisibility(View.INVISIBLE);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllApartmentsActivity.this,
                    AddApartmentActivity.class));

                finish();
            }
        });

        String MY_PREFS_NAME = "newAddress";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String address = prefs.getString("address", "not defined address");




/*
            boolean isApt1Vis = false;
            boolean isApt2Vis = false;
            boolean isApt3Vis = false;

        final TextView apt1 = findViewById(R.id.apt1_descr);
        final TextView apt2 = findViewById(R.id.apt2_description);
        final TextView apt3 = findViewById(R.id.apt3_description);

            if(!(isApt1Vis)) {
                    aptRow1.setVisibility(View.VISIBLE);
                    isApt1Vis = true;
                    apt1.setText(address);
                }
                else if (!(isApt2Vis)) {
                    aptRow2.setVisibility(View.VISIBLE);
                    isApt2Vis = true;
                    apt2.setText(address);
                }
                else if (!(isApt3Vis)) {
                    aptRow3.setVisibility(View.VISIBLE);
                    isApt3Vis = true;
                    apt3.setText(address);
                }

                back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllApartmentsActivity.this,
                        FeaturesActivity.class));
            }
        });*/
    }
}
