package personal.nmartinez.fr.virtualfootballpicker.components.createwheels.presenter;

import personal.nmartinez.fr.virtualfootballpicker.data.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.data.models.WheelModel;

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
