package hciadk.apartmenthunters;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ApartmentViewModel mViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

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

        mViewModel = ViewModelProviders.of(this).get(ApartmentViewModel.class);
        mViewModel.getmAllApts().observe(this, new Observer<List<Apartment>>() {
            @Override
            public void onChanged(@Nullable final List<Apartment> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        });

        final TableRow aptRow1 = findViewById(R.id.apt1);
        final TableRow aptRow2 = findViewById(R.id.apt2);
        final TableRow aptRow3 = findViewById(R.id.apt3);
        Button back = findViewById(R.id.back_to_checklist);
        ImageButton newApt = findViewById(R.id.add_new_apt);
        RatingBar ratingBar = findViewById(R.id.ratingBar);

        aptRow1.setVisibility(View.INVISIBLE);
        aptRow2.setVisibility(View.INVISIBLE);
        aptRow3.setVisibility(View.INVISIBLE);

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
            boolean isApt1Vis = false;
            boolean isApt2Vis = false;
            boolean isApt3Vis = false;
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
                if(isApt1Vis) {
                    aptRow1.setVisibility(View.VISIBLE);
                    isApt1Vis = true;
                }
                else if (isApt2Vis) {
                    aptRow2.setVisibility(View.VISIBLE);
                    isApt2Vis = true;
                }
                else if (isApt3Vis) {
                    aptRow3.setVisibility(View.VISIBLE);
                    isApt3Vis = true;
                }


                Log.d("did the apt db work", mViewModel.getmAllApts().toString());
            }
        });

//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                mViewModel.setRating(rating, 1);
//            }
//        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Apartment word = new Apartment(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
            mViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
