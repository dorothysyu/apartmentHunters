package hciadk.apartmenthunters.apartments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hciadk.apartmenthunters.R;

public class ExtraNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_notes);


/*        Button extraNotesBtn = findViewById(R.id.extra_notes);

        extraNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExtraNotesActivity.this,
                        ApartmentEditActivity.class));
            }
        });*/
    }
}
