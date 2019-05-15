package personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective;

import android.arch.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import personal.nmartinez.fr.virtualfootballpicker.SpinGoalApp;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.interfaces.CreateObjectiveRepositoryListener;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.interfaces.DeleteObjectiveRepositoryListener;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.interfaces.ObjectiveRepository;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.interfaces.UpdateObjectiveRepositoryListener;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;

public class ObjectiveRepositoryImpl implements ObjectiveRepository {

    private Executor executor;

    public ObjectiveRepositoryImpl() {
        executor = Executors.newFixedThreadPool(2);
    }

    @Override
    public LiveData<List<Objective>> getObjectives() {
        return SpinGoalApp.getApp().getDatabase().objectiveDao().getObjectives();
    }

    @Override
    public LiveData<List<Objective>> getObjectivesByWheelAndPeriod(int wheelId, int period) {
        return SpinGoalApp.getApp().getDatabase().wheelObjectiveJoinDao().getObjectivesByPeriodForWheel(wheelId, period);
    }

    @Override
    public LiveData<List<Objective>> getObjectivesByWheel(int wheelId) {
        return SpinGoalApp.getApp().getDatabase().wheelObjectiveJoinDao().getObjectivesForWheel(wheelId);
    }

    @Override
    public void createObjective(Objective objective, CreateObjectiveRepositoryListener listener) {
        executor.execute(() -> {
            long result = SpinGoalApp.getApp().getDatabase().objectiveDao().createObjective(objective);
            if (listener != null) {
                if (result != 0) {
                    listener.onCreateObjectiveSuccess();
                } else {
                    listener.onCreateObjectiveFailure();
                }
            }
        });
    }

    @Override
    public void createObjectives(List<Objective> objectives, CreateObjectiveRepositoryListener listener) {
        executor.execute(() -> {
            List<Long> result = SpinGoalApp.getApp().getDatabase().objectiveDao().createObjectives(objectives);
            if (listener != null) {
                if (result != null) {
                    listener.onCreateObjectiveSuccess();
                } else {
                    listener.onCreateObjectiveFailure();
                }
            }
        });
    }

    @Override
    public void updateObjective(Objective objective, UpdateObjectiveRepositoryListener listener) {
        executor.execute(() -> {
            int objectivesUpdated = SpinGoalApp.getApp().getDatabase().objectiveDao().updateObjective(objective);
            if (listener != null) {
                if (objectivesUpdated > 0) {
                    listener.onUpdateObjectiveSuccess();
                } else {
                    listener.onUpdateObjectiveSuccess();
                }
            }
        });
    }

    @Override
    public void deleteObjective(Objective objective, DeleteObjectiveRepositoryListener listener) {
        executor.execute(() -> {
            int objectivesDeleted = SpinGoalApp.getApp().getDatabase().objectiveDao().deleteObjective(objective);
            SpinGoalApp.getApp().getDatabase().wheelObjectiveJoinDao().deleteFromObjective(objective.getId());

            if (listener != null) {
                if (objectivesDeleted > 0) {
                    listener.onDeleteObjectiveSuccess();
                } else {
                    listener.onDeleteObjectiveFailure();
                }
            }
        });
    }
}
