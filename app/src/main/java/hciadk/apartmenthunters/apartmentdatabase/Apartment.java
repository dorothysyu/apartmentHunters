package hciadk.apartmenthunters.apartmentdatabase;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "apartment")

public class Apartment {

    @PrimaryKey(autoGenerate = true)
    private int aptID;

    @NonNull
    @ColumnInfo(name = "address")
    private String mAddress;

    @ColumnInfo(name = "rating")
    private double mRating;

    @ColumnInfo(name = "price")
    private double mPrice;

    public Apartment(@NonNull String mAddress) {
        this.mAddress = mAddress;
    }

    public int getId() { return this.aptID; }

    public String getmAddress() { return this.mAddress; }

    public double getmRating() { return this.mRating; }

    public double getmPrice() { return this.mPrice; }
}