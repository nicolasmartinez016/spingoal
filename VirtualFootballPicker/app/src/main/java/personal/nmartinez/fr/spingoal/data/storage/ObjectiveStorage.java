package personal.nmartinez.fr.spingoal.data.storage;

import android.arch.lifecycle.LiveData;

import java.util.List;

import personal.nmartinez.fr.spingoal.data.models.Objective;

public interface ObjectiveStorage {

    LiveData<List<Objective>> getObjectives();

    LiveData<List<Objective>> getWheelObjectivesByPeriod(int wheelId, int period);

    boolean createObjective(Objective objective);

    boolean updateObjective(Objective objective);

    boolean deleteObjective(Objective objective);

    LiveData<List<Objective>> getWheelObjectives(int wheelId);
}
