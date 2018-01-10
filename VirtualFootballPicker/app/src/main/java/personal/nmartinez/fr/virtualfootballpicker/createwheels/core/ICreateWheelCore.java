package personal.nmartinez.fr.virtualfootballpicker.createwheels.core;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.models.Objective;

/**
 * Created by Nicolas on 04/01/2018.
 */

public interface ICreateWheelCore {

    void applyCreation();
    void addOrRemoveObjective(Objective objective);
    List<Objective> getAllObjectives();
    boolean isObjectiveInWheel(Objective objective);
}
