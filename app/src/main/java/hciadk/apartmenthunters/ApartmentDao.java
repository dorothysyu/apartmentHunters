package hciadk.apartmenthunters;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ApartmentDao {

    //Inserting into Apartment table
    @Insert
    void insertApartment(Apartment apt);

//    //Insert into Feature table
//    @Insert
//    void insertFeature(Feature feat);
//
//    //Insert into ApartmentFeature table
//    @Insert
//    void insertApartmentFeature(ApartmentFeature aptFeat);

    @Query("DELETE FROM apartment_table")
    void deleteAll();

    @Query("SELECT * from apartment_table")
    LiveData<List<Apartment>> getAllApartments();

    @Query("UPDATE apartment_table SET rating=:rating WHERE aptID = :id")
    void updateRating(Float rating, int id);

}
