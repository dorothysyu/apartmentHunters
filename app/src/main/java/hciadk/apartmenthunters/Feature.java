package hciadk.apartmenthunters;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "features")
public class Feature {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "featureID")
    private int featureID;

    @NonNull
    @ColumnInfo(name = "feature")
    private String feature;

    @ColumnInfo(name = "isNecessary")
    private boolean isNecessary;

    public Feature(@NonNull String feature, boolean isNecessary) {
        this.feature = feature;
        this.isNecessary = isNecessary;
    }

    @NonNull
    public int getFeatureID() { return featureID; }

    public String getFeature() { return feature; }

    public boolean isNecessary() { return isNecessary; }
}
