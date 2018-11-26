package hciadk.apartmenthunters.settings;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface GroupDao {
    @Insert
    void insert(Group group);

    @Query("DELETE FROM group_table")
    void deleteAll();

    @Query("SELECT * from group_table ORDER BY group_users ASC")
    LiveData<List<Group>> getAllGroups();
}
