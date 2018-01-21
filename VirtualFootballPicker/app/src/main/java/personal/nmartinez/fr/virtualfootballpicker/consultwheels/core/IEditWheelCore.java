package personal.nmartinez.fr.virtualfootballpicker.consultwheels.core;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;

/**
 * Created by Nicolas on 16/12/2017.
 */

public interface IEditWheelCore {

    void addOrRemoveObjective(Objective objective);
    boolean saveChanges();
    Wheel getWheel();
    List<Objective> getObjectives();
    boolean isObjectiveInWheel(Objective objective);
}
