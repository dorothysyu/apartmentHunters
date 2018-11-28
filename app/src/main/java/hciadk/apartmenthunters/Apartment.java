package hciadk.apartmenthunters;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "apartment_table")

public class Apartment {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "aptID")
    private int aptID;

    @NonNull
    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "rating")
    private double rating;

    @ColumnInfo(name = "maxPrice")
    private double price;

    public Apartment(@NonNull String address) {
        this.address = address;
        this.aptID = aptID;
        this.rating = rating;
        this.price = price;
    }

    public int getAptID() { return this.aptID; }

    @NonNull
    public String getAddress() {
        return address;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public void setAptID(@NonNull int aptID) {
        this.aptID = aptID;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}