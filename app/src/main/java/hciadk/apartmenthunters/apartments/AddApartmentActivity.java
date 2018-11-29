package hciadk.apartmenthunters.apartments;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hciadk.apartmenthunters.R;

/**
 * Activity for entering a word.
 */

public class AddApartmentActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditWordView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);

        final Button button = findViewById(R.id.button_save);

        String MY_PREFS_NAME = "apts";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        int i = 0;
        while(prefs.getBoolean("apt"+i, true)) {
            i++;
        }
        final int k = i;

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String MY_PREFS_NAME = "newAddress";

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                //pass the address
                editor.putString("address" + k, mEditWordView.getText().toString());
                //pass the apt to make visible
                editor.putInt("flipVisibility", k);
                editor.apply();
                startActivity(new Intent(AddApartmentActivity.this,
                        AllApartmentsActivity.class));
                finish();
            }
        });
    }
}
