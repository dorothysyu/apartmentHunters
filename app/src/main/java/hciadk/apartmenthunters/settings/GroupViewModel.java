package hciadk.apartmenthunters.settings;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class GroupViewModel extends AndroidViewModel {
    private LiveData<List<Group>> mAllGroups;
    private GroupRepository mRepository;

    public GroupViewModel(Application application) {
        super(application);
        mRepository = new GroupRepository(application);
        mAllGroups = mRepository.getAllGroups();
    }

    public void init() {
        this.mAllGroups = new MutableLiveData<>();
    }

    public LiveData<List<Group>> getRoommates() {
        Log.d("print roommates", this.mAllGroups + "");
        return this.mAllGroups;
    }

    LiveData<List<Group>> getAllGroups() { return this.mAllGroups; }

    public void insert(Group group) { mRepository.insert(group); }


}
