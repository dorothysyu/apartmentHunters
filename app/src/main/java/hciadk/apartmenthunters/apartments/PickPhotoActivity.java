package hciadk.apartmenthunters.apartments;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import java.util.HashMap;

import hciadk.apartmenthunters.R;

public class PickPhotoActivity extends AppCompatActivity {

    //Pairs the check box IDs to the ImageView ids
    HashMap<String, String> pairedImageIDs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_photo);

        ConstraintLayout gallery = findViewById(R.id.gallery);
        final Button select = findViewById(R.id.select_photos);

        select.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

       /*         do something with the selected photos
                savePreferences(selectedPhotos);*/

                startActivity(new Intent(PickPhotoActivity.this,
                        ApartmentEditActivityAfterAddingPhoto.class));
                finish();
            }
        });
    }
}
