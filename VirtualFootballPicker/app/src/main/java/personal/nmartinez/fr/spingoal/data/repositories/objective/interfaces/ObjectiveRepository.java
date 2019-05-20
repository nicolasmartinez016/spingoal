package personal.nmartinez.fr.spingoal.data.repositories.objective.interfaces;

import android.arch.lifecycle.LiveData;

import java.util.List;

import personal.nmartinez.fr.spingoal.data.models.Objective;

public interface ObjectiveRepository {

    LiveData<List<Objective>> getObjectives();

    LiveData<List<Objective>> getObjectivesByWheelAndPeriod(int wheelId, int period);

    LiveData<List<Objective>> getObjectivesByWheel(int wheelId);

    void createObjective(Objective objective, CreateObjectiveRepositoryListener listener);

    void createObjectives(List<Objective> objectives, CreateObjectiveRepositoryListener listener);

    void updateObjective(Objective objective, UpdateObjectiveRepositoryListener listener);

    void deleteObjective(Objective objective, DeleteObjectiveRepositoryListener listener);
}
