package hciadk.apartmenthunters.apartmentdatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

@Dao
public interface ApartmentDao {

    //Inserting into Apartment table
    @Insert
    void insertApartment(Apartment apt);

    //Insert into Feature table
    @Insert
    void insertFeature(Feature feat);

    //Insert into ApartmentFeature table
    @Insert
    void insertApartmentFeature(ApartmentFeature aptFeat);

    




}
