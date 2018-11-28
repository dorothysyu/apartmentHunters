package hciadk.apartmenthunters.apartments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;

import hciadk.apartmenthunters.R;
import hciadk.apartmenthunters.settings.FeaturesActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart(){

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
