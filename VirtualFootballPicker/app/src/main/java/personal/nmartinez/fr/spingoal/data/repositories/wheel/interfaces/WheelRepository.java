package personal.nmartinez.fr.spingoal.data.repositories.wheel.interfaces;

import android.arch.lifecycle.LiveData;

import java.util.List;

import personal.nmartinez.fr.spingoal.data.models.FavoriteWheel;
import personal.nmartinez.fr.spingoal.data.models.Wheel;

public interface WheelRepository {

    LiveData<List<Wheel>> getWheels();
    LiveData<Wheel> getWheelById(int id);
    void saveFavoriteWheel(FavoriteWheel favoriteWheel);
    void createWheel(Wheel wheel, CreateWheelRepositoryListener listener);
    void updateWheel(Wheel wheel, UpdateWheelRepositoryListener listener);
    void deleteWheel(Wheel wheel, DeleteWheelRepositoryListener listener);

    LiveData<FavoriteWheel> getFavoriteWheel();
}
