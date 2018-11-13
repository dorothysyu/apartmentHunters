package hciadk.apartmenthunters.settings;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.List;

public class GroupRepository {
    private GroupDao mGroupDao;
    private LiveData<List<Group>> mAllGroups;

    GroupRepository(Application application) {
        GroupRoomDatabase db = GroupRoomDatabase.getDatabase(application);
        mGroupDao = db.groupDao();
        mAllGroups = mGroupDao.getAllGroups();
    }

    LiveData<List<Group>> getAllGroups() {
        return mAllGroups;
    }

    public void insert (Group group) {
        new insertAsyncTask(mGroupDao).execute(group);
    }

    private static class insertAsyncTask extends AsyncTask<Group, Void, Void> {

        private GroupDao mAsyncTaskDao;

        insertAsyncTask(GroupDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Group... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
