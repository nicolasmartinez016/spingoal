package personal.nmartinez.fr.spingoal.data.storage;

import java.util.List;

import personal.nmartinez.fr.spingoal.data.models.Wheel;

public interface WheelStorage {

    List<Wheel> getWheels();

    boolean createWheel(Wheel wheel);

    boolean updateWheel(Wheel wheel);

    boolean deleteWheel(Wheel wheel);

    Wheel getFavoriteWheel();

    boolean saveFavoriteWheel(Wheel wheel);
}
