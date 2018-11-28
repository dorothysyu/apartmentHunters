package hciadk.apartmenthunters;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

public class ApartmentViewModel extends AndroidViewModel {

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
        Log.d("print apartments", this.mAllApts + "");
        return this.mAllApts;
    }

    public void setRating(Float rating, int id) { mRepository.setRating(rating, id);}

    public void insert(Apartment apartment) { mRepository.insert(apartment); }


}
