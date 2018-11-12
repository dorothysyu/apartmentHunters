package hciadk.apartmenthunters;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class RoommatesViewModel extends ViewModel {
    private MutableLiveData<ArrayList<String>> roommates;

    public void init() {
        this.roommates = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<String>> getRoommates() {
        Log.d("print roommates", this.roommates + "");
        return this.roommates;
    }

}
