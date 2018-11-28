package hciadk.apartmenthunters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {
    

    @Override
    protected void onStart(){

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview2);
        final ApartmentListAdapter adapter = new ApartmentListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TableRow aptRow1 = findViewById(R.id.apt1);
        TableRow aptRow2 = findViewById(R.id.apt2);
        TableRow aptRow3 = findViewById(R.id.apt3);
        Button back = findViewById(R.id.back_to_checklist);
        ImageButton newApt = findViewById(R.id.add_new_apt);

        aptRow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        ApartmentEditActivity.class));
            }
        });

        aptRow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        ApartmentEditActivity.class));
            }
        });

        aptRow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        ApartmentEditActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        FeaturesActivity.class));
            }
        });

        newApt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewWordActivity.class));
            }
        });
    }
}
