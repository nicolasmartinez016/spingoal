package personal.nmartinez.fr.virtualfootballpicker.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;
import personal.nmartinez.fr.virtualfootballpicker.models.WheelObjectiveJoin;

@Dao
public abstract class WheelObjectiveJoinDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void createWheelObjectiveJoin(WheelObjectiveJoin wheelObjectiveJoin);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void createWheelObjectiveJoins(List<WheelObjectiveJoin> wheelObjectiveJoins);

    @Query("SELECT * FROM Wheels INNER JOIN wheel_objective_join ON wheels.id = wheel_objective_join.wheelId WHERE wheel_objective_join.objectiveId=:objectiveId")
    public abstract LiveData<List<Wheel>> getWheelsForObjective(final int objectiveId);

    @Query("SELECT * FROM Objectives INNER JOIN wheel_objective_join ON Objectives.id = wheel_objective_join.objectiveId WHERE wheel_objective_join.wheelId=:wheelId")
    public abstract LiveData<List<Objective>> getObjectivesForWheel(final int wheelId);

    @Query("SELECT * FROM Objectives INNER JOIN wheel_objective_join ON Objectives.id = wheel_objective_join.objectiveId WHERE wheel_objective_join.wheelId=:wheelId AND Objectives.period=:period OR Objectives.period=3")
    public abstract LiveData<List<Objective>> getObjectivesByPeriodForWheel(final int wheelId, final int period);

    @Query("DELETE FROM wheel_objective_join WHERE wheelId=:wheelId")
    public abstract int deleteWheelObjectives(int wheelId);

    @Query("DELETE FROM wheel_objective_join WHERE objectiveId=:objectiveId")
    public abstract void deleteFromObjective(int objectiveId);

    @Transaction
    public void deleteAndInsertInTransaction(Wheel wheel) {
        if (wheel != null) {
            deleteWheelObjectives(wheel.getId());
            createWheelObjectiveJoins(WheelObjectiveJoin.fromWheel(wheel));
        }
    }

}
