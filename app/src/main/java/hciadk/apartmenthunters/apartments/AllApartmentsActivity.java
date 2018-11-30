package hciadk.apartmenthunters.apartments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import hciadk.apartmenthunters.R;
import hciadk.apartmenthunters.settings.AddRoommatesActivity;
import hciadk.apartmenthunters.settings.FeaturesActivity;

import static android.view.View.VISIBLE;

public class AllApartmentsActivity extends AppCompatActivity {




    ArrayList<View> apts = new ArrayList<>();
    ArrayList<TextView> desc = new ArrayList<>();
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

        final TextView apt1desc = findViewById(R.id.apt1_descr);
        final TextView apt2desc = findViewById(R.id.apt2_description);
        final TextView apt3desc = findViewById(R.id.apt3_description);

        final ImageButton view1 = findViewById(R.id.view1);
        final ImageButton view2 = findViewById(R.id.view2);
        final ImageButton view3 = findViewById(R.id.view3);

        final Button filter = findViewById(R.id.filter);

        receiveFilters();

        apts.add(aptRow1);
        apts.add(aptRow2);
        apts.add(aptRow3);

        desc.add(apt1desc);
        desc.add(apt2desc);
        desc.add(apt3desc);

        ImageButton add = findViewById(R.id.add_new_apt);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllApartmentsActivity.this,
                        FilterActivity.class));

                finish();
            }
        });

        aptRow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllApartmentsActivity.this,
                        ApartmentEditActivity.class));

                whichApt(1);
            }
        });

        aptRow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllApartmentsActivity.this,
                        ApartmentEditActivity.class));

                whichApt(2);
            }
        });

        aptRow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllApartmentsActivity.this,
                        ApartmentEditActivity.class));

                whichApt(3);
            }
        });


        loadPreferences();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllApartmentsActivity.this,
                    AddApartmentActivity.class));

                savePreferences();
                finish();
            }
        });

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllApartmentsActivity.this,
                        ApartmentFinalActivity.class));
            }
        });

        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllApartmentsActivity.this,
                        ApartmentFinalActivity.class));
            }
        });

        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllApartmentsActivity.this,
                        ApartmentFinalActivity.class));
            }
        });

    }

    public void whichApt(int aptNum) {
        String MY_PREFS_NAME = "whichApt";

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        editor.putInt("apt", aptNum);

        editor.apply();
        Log.d("allapt aptnum", "apt num" + " " + aptNum);
    }

    public void receiveFilters() {
        Log.d("WHY ARE YOU SHOWING", "SHOW UP");
        String name = "filter";
        SharedPreferences sharedPreferences = getSharedPreferences(name, MODE_PRIVATE);

        int size = sharedPreferences.getInt("sizeOfFilters", 0);

        Log.d("sizeOfFilters received", size + "");

        for (int i = 0; i < size; i++) {
            String filter = sharedPreferences.getString("filter"+i, "Undefined filter");
            Log.d("received filters", filter);
        }
    }

    public void savePreferences() {
        /* so we need to make a list of the apartments visibility
        * like 1 true, 2 false, 3 false
        * pass it to the addApartment activity
        * where in onclick, it would go through the array of booleans
        * take the first false as the apartment to flip visibility
        * pass back the apartment number (ex. 2) and given address to the allApartmentActivity
        * this would signal that you need to make apartment 2 visible
        * then set the text displaying to the given address from sharedPref
        * yay */

        String MY_PREFS_NAME = "apts";

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        editor.putInt("size", 3);

        int i = 0;
        for (View apt:apts) {
            if(apt.getVisibility() == VISIBLE) {
                editor.putBoolean("apt" + i, true);

                Log.d("visible", "apt" + i);
            }
            else {
                editor.putBoolean("apt" + i, false);
                Log.d("invisible", "apt" + i);
            }
            i++;
        }

        Log.d("i'm reached", "yay");

        editor.apply();
    }

    public void loadPreferences() {
        String MY_PREFS_NAME = "newAddress";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        int flipApt = prefs.getInt("flipVisibility", -1);
        String address;
        Log.d("flip apt", flipApt + "");

        for(int i =0; i < flipApt + 1; i++) {
            apts.get(i).setVisibility(VISIBLE);
            address = prefs.getString("address"+i, "invalid address");
            desc.get(i).setText(address);
        }
    }
}
