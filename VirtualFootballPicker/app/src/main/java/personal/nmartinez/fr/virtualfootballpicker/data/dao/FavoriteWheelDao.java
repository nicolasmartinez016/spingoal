package personal.nmartinez.fr.virtualfootballpicker.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import personal.nmartinez.fr.virtualfootballpicker.models.FavoriteWheel;

@Dao
public interface FavoriteWheelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createFavoriteWheel(FavoriteWheel favoriteWheel);

    @Query("select * from FavoriteWheel where id =:id")
    LiveData<FavoriteWheel> getFavoriteWheel(int id);
}
