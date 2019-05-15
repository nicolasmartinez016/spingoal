package personal.nmartinez.fr.virtualfootballpicker.createwheels.presenter;

import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.models.WheelModel;

/**
 * Created by Nicolas on 04/01/2018.
 */

public interface CreateWheelPresenter {

    void createWheel(WheelModel wheelModel);
    void addOrRemoveObjective(Objective objective);
    boolean isObjectiveInWheel(Objective objective);

    void initViews();

    void addOrRemoveAllObjectives(boolean shouldAdd);
}
