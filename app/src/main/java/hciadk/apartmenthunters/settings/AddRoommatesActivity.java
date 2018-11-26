package hciadk.apartmenthunters.settings;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import hciadk.apartmenthunters.FeaturesActivity;
import hciadk.apartmenthunters.R;

public class AddRoommatesActivity extends AppCompatActivity {
    private GroupViewModel mViewModel;
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
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final GroupListAdapter rAdapter = new GroupListAdapter(this);
        recyclerView.setAdapter(rAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mViewModel = ViewModelProviders.of(this).get(GroupViewModel.class);
        mViewModel.getAllGroups().observe(this, new Observer<List<Group>>() {
            @Override
            public void onChanged(@Nullable final List<Group> groups) {
                // Update the cached copy of the words in the adapter.
                rAdapter.setGroups(groups);
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, USERS);
        final AutoCompleteTextView textView = findViewById(R.id.type_user);
        textView.setAdapter(adapter);

        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                int pos = Arrays.asList(USERS).indexOf(selected);

                Log.d("list item", selected);

                String roommateSelected = Arrays.asList(USERNAMES).get(pos);

                textView.setText(roommateSelected);

//                roommates.add(roommateSelected);
//                viewModel.getRoommates().setValue(roommates);
//                Log.d("roomies", roommates + "");
            }
        });

        Button add = findViewById(R.id.add_feature);

        add.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String typed = textView.getText().toString();
            Group group = new Group(typed);
            mViewModel.insert(group);

             Log.d("did the db work", mViewModel.getRoommates().toString());
         }
                               });



//        Observer<List<Group>> roommatesObserver = new Observer<List<Group>>() {
//            @Override
//            public void onChanged(@Nullable ArrayList<String> strings) {
//                setRoommates(strings);
//            }
//        };
//        viewModel.init();
//        viewModel.getRoommates().observe(this, roommatesObserver);
//

    }

    private void setRoommates(ArrayList<String> roommates) {
        this.roommates = roommates;
    }
}
