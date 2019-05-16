package personal.nmartinez.fr.virtualfootballpicker.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import personal.nmartinez.fr.virtualfootballpicker.data.dao.FavoriteWheelDao;
import personal.nmartinez.fr.virtualfootballpicker.data.dao.ObjectiveDao;
import personal.nmartinez.fr.virtualfootballpicker.data.dao.WheelDao;
import personal.nmartinez.fr.virtualfootballpicker.data.dao.WheelObjectiveJoinDao;
import personal.nmartinez.fr.virtualfootballpicker.data.models.FavoriteWheel;
import personal.nmartinez.fr.virtualfootballpicker.data.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.data.models.Wheel;
import personal.nmartinez.fr.virtualfootballpicker.data.models.WheelObjectiveJoin;

@Database(entities = {Objective.class, Wheel.class, WheelObjectiveJoin.class, FavoriteWheel.class}, version = 1, exportSchema = false)
public abstract class SpinGoalDatabase extends RoomDatabase {

    public abstract ObjectiveDao objectiveDao();
    public abstract WheelDao wheelDao();
    public abstract FavoriteWheelDao favoriteWheelDao();
    public abstract WheelObjectiveJoinDao wheelObjectiveJoinDao();


}
