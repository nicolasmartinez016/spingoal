package personal.nmartinez.fr.virtualfootballpicker.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.data.models.Objective;

@Dao
public interface ObjectiveDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long createObjective(Objective objective);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> createObjectives(List<Objective> objectives);

    @Update
    int updateObjective(Objective objective);

    @Delete
    int deleteObjective(Objective objective);

    @Query("SELECT * FROM Objectives")
    LiveData<List<Objective>> getObjectives();
}
