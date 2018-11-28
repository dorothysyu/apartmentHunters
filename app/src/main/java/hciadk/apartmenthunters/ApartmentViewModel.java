package hciadk.apartmenthunters;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

public class ApartmentViewModel extends GroupViewModel {

    private LiveData<List<Apartment>> mAllApts;
    private ApartmentRepository mRepository;

    public ApartmentViewModel(Application application) {
        super(application);
        mRepository = new ApartmentRepository(application);
        mAllApts = mRepository.getmAllApts();
    }

    public void init() {
        this.mAllApts = new MutableLiveData<>();
    }

    public LiveData<List<Apartment>> getmAllApts() {
        Log.d("print roommates", this.mAllApts + "");
        return this.mAllApts;
    }

    public void insert(Apartment apartment) { mRepository.insert(apartment); }


}
