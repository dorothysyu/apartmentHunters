package hciadk.apartmenthunters.settings;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "group_table")

public class Group {
//    @PrimaryKey(autoGenerate = true)
//    private int mId;
//    private int mGroup;
//    private String mUser;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "group")
    private String mGroup;

    public Group(@NonNull String group) {
            //, @NonNull String user, @NonNull int mId) {
        this.mGroup = group;
//        this.mUser = user;
//        this.mId = mId;
    }

    public String getGroup(){return this.mGroup;}

//    public String getmUser() { return this.mUser;}
//
//    public int getmId() {return this.mId;}



}
