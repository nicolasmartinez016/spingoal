package personal.nmartinez.fr.virtualfootballpicker.data.repositories.wheel;

import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.application.SpinGoalApp;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.wheel.interfaces.CreateWheelRepositoryListener;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.wheel.interfaces.DeleteWheelRepositoryListener;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.wheel.interfaces.UpdateWheelRepositoryListener;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.wheel.interfaces.WheelRepository;
import personal.nmartinez.fr.virtualfootballpicker.data.models.FavoriteWheel;
import personal.nmartinez.fr.virtualfootballpicker.data.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.data.models.Wheel;
import personal.nmartinez.fr.virtualfootballpicker.data.models.WheelObjectiveJoin;

public class WheelRepositoryImpl implements WheelRepository {


    public WheelRepositoryImpl() {
    }

    @Override
    public LiveData<FavoriteWheel> getFavoriteWheel() {
        return SpinGoalApp.getApp().getDatabase().favoriteWheelDao().getFavoriteWheel(FavoriteWheel.ID);
    }

    @Override
    public void saveFavoriteWheel(FavoriteWheel favoriteWheel) {
        new Thread(() -> {
            SpinGoalApp.getApp().getDatabase().favoriteWheelDao().createFavoriteWheel(favoriteWheel);
        }
        ).start();
    }

    @Override
    public LiveData<Wheel> getWheelById(int id) {
        return SpinGoalApp.getApp().getDatabase().wheelDao().getWheelById(id);
    }

    @Override
    public LiveData<List<Wheel>> getWheels() {
        return SpinGoalApp.getApp().getDatabase().wheelDao().getWheels();
    }

    @Override
    public void createWheel(Wheel wheel, CreateWheelRepositoryListener listener) {
        List<WheelObjectiveJoin> wheelObjectiveJoins = new ArrayList<>();
        for (Objective localObjective : wheel.getObjectives()) {
            wheelObjectiveJoins.add(new WheelObjectiveJoin(wheel.getId(), localObjective.getId()));
        }

        new Thread(() -> {
            long id = SpinGoalApp.getApp().getDatabase().wheelDao().createWheel(wheel);
            SpinGoalApp.getApp().getDatabase().wheelObjectiveJoinDao().createWheelObjectiveJoins(wheelObjectiveJoins);

            if (id != 0) {
                listener.onCreateWheelSuccess();
            } else {
                listener.onCreateWheelFailure();
            }
        }
        ).start();
    }

    @Override
    public void updateWheel(Wheel wheel, UpdateWheelRepositoryListener listener) {
        new Thread(() -> {
            int wheelsUpdated = SpinGoalApp.getApp().getDatabase().wheelDao().updateWheel(wheel);
            SpinGoalApp.getApp().getDatabase().wheelObjectiveJoinDao().deleteAndInsertInTransaction(wheel);

            if (wheelsUpdated > 0) {
                listener.onUpdateWheelSuccess();
            } else {
                listener.onUpdateWheelFailure();
            }
        }
        ).start();
    }

    @Override
    public void deleteWheel(Wheel wheel, DeleteWheelRepositoryListener listener) {
        new Thread(() -> {
            int wheelsDeleted = SpinGoalApp.getApp().getDatabase().wheelDao().deleteWheel(wheel);
            int joinsDeleted = SpinGoalApp.getApp().getDatabase().wheelObjectiveJoinDao().deleteWheelObjectives(wheel.getId());

            if (wheelsDeleted > 0) {
                listener.onDeleteWheelSuccess();
            } else {
                listener.onDeleteWheelFailure();
            }
        }).start();
    }
}
