package hciadk.apartmenthunters;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ApartmentRepository {
    private ApartmentDao mAptDao;
    private final ApartmentDatabase db;
    private LiveData<List<Apartment>> mAllApts;

    ApartmentRepository(Application application) {
        db = ApartmentDatabase.getDatabase(application);
        mAptDao = db.aptDao();
        mAllApts = mAptDao.getAllApartments();
    }

    LiveData<List<Apartment>> getmAllApts() {
        return mAllApts;
    }

    public void insert (Apartment apartment) {
        new ApartmentRepository.insertAsyncTask(mAptDao).execute(apartment);
    }

    public void setRating(final float rating, int id) {
        db.aptDao().updateRating(rating, id);
    }

    private static class insertAsyncTask extends AsyncTask<Apartment, Void, Void> {

        private ApartmentDao mAsyncTaskDao;

        insertAsyncTask(ApartmentDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Apartment... params) {
            mAsyncTaskDao.insertApartment(params[0]);
            return null;
        }
    }
}
