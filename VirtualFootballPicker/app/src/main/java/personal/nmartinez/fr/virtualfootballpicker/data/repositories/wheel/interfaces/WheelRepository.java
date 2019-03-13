package personal.nmartinez.fr.virtualfootballpicker.data.repositories.wheel.interfaces;

import android.arch.lifecycle.LiveData;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.models.FavoriteWheel;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;

public interface WheelRepository {

    LiveData<List<Wheel>> getWheels();
    LiveData<Wheel> getWheelById(int id);
    void saveFavoriteWheel(FavoriteWheel favoriteWheel);
    void createWheel(Wheel wheel, CreateWheelRepositoryListener listener);
    void updateWheel(Wheel wheel, UpdateWheelRepositoryListener listener);
    void deleteWheel(Wheel wheel, DeleteWheelRepositoryListener listener);

    LiveData<FavoriteWheel> getFavoriteWheel();
}
