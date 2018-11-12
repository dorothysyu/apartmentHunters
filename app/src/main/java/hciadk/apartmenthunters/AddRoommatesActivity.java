package hciadk.apartmenthunters;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

public class AddRoommatesActivity extends AppCompatActivity {
    RoommatesViewModel viewModel;
    ArrayList roommates;

    String lineSep = System.getProperty("line.separator");
    private final String[] USERS = new String[] {
            "@jackchen | (617)439-1039 | jackiechen@gmail.com",
            "@james101 | (617)325-1294 | jamesrocketpower@gmail.com",
            "@katieleesmith | (617)542-4321 | kls@gmail.com"
    };

    private final String[] USERNAMES = new String[] {
            "@jackchen",
            "@james101",
            "@katieleesmith"
    };

    @Override
    protected void onStart() {
        super.onStart();

        Button skipBtn = findViewById(R.id.skip_add_roommates);
        Button continueBtn = findViewById(R.id.add_roommate);

        //Observer<ArrayList<String>> roommateObserver =

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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_roommates_activity);
        roommates = new ArrayList();

        viewModel = ViewModelProviders.of(this).get(RoommatesViewModel.class);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, USERS);
        final AutoCompleteTextView textView = findViewById(R.id.type_user);
        textView.setAdapter(adapter);


        Observer<ArrayList<String>> roommatesObserver = new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(@Nullable ArrayList<String> strings) {
                setRoommates(strings);
            }
        };
        viewModel.init();
        viewModel.getRoommates().observe(this, roommatesObserver);

        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                int pos = Arrays.asList(USERS).indexOf(selected);

                Log.d("list item", selected);

                String roommateSelected = Arrays.asList(USERNAMES).get(pos);

                textView.setText(roommateSelected);

                roommates.add(roommateSelected);
                viewModel.getRoommates().setValue(roommates);
                Log.d("roomies", roommates + "");
            }
        });
    }

    private void setRoommates(ArrayList<String> roommates) {
        this.roommates = roommates;
    }
}
