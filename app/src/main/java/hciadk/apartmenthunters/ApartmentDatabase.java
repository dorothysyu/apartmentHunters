package hciadk.apartmenthunters;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Group.class, Apartment.class}, version = 1)
public abstract class ApartmentDatabase extends RoomDatabase {
    public abstract GroupDao groupDao();
    public abstract ApartmentDao aptDao();
    private static volatile ApartmentDatabase INSTANCE;

    static ApartmentDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ApartmentDatabase.class) {
                if (INSTANCE == null) {
                    //Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ApartmentDatabase.class, "apartment_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     *
     * If you want to populate the database only when the database is created for the 1st time,
     * override RoomDatabase.Callback()#onCreate
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final GroupDao mGroupDao;
        private final ApartmentDao mApartmentDao;

        PopulateDbAsync(ApartmentDatabase db) {
            mGroupDao = db.groupDao();
            mApartmentDao = db.aptDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mGroupDao.deleteAll();
            mApartmentDao.deleteAll();

//            Group word = new Group("Hello");
//            mDao.insert(word);
//            word = new Group("World");
//            mDao.insert(word);
//            Log.d("does db exist", (INSTANCE != null) + "");
            return null;
        }
    }

}