package hciadk.apartmenthunters.apartmentdatabase;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(primaryKeys = {"featureID", "aptID"},
        tableName = "apartment_features",
        foreignKeys = {
            @ForeignKey(entity = Apartment.class,
                parentColumns = "aptID",
                childColumns = "aptID"),
            @ForeignKey(entity = Feature.class,
                parentColumns = "featureID",
                childColumns = "featureID")})

public class ApartmentFeature {

    @PrimaryKey
    @ColumnInfo(name = "featureID")
    public int aptFeatID;

    @PrimaryKey
    @ColumnInfo(name = "aptID")
    @NonNull
    public int aptID;

    @ColumnInfo(name = "hasFeature")
    public boolean hasFeature;

    public ApartmentFeature() {}

    public int getAptFeatID() { return aptFeatID; }

    @NonNull
    public int getAptID() { return aptID; }

    public boolean isHasFeature() { return hasFeature; }
}
