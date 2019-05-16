package personal.nmartinez.fr.virtualfootballpicker.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.data.models.Wheel;

@Dao
public interface WheelDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long createWheel(Wheel wheel);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] createWheels(List<Wheel> wheels);

    @Update
    int updateWheel(Wheel wheel);

    @Delete
    int deleteWheel(Wheel wheel);

    @Query("SELECT * FROM Wheels")
    LiveData<List<Wheel>> getWheels();

    @Query("SELECT * FROM Wheels WHERE id=:id")
    LiveData<Wheel> getWheelById(int id);
}
