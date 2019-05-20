package personal.nmartinez.fr.spingoal.components.createwheels.view;

import android.arch.lifecycle.LifecycleOwner;

import personal.nmartinez.fr.spingoal.data.models.WheelModel;

/**
 * Created by Nicolas on 04/01/2018.
 */

public interface CreateWheelView extends LifecycleOwner {
    void showCreationSuccess();
    void showCreationFailure();

    void initViews(WheelModel wheelModel);

    void showNameNotAvailable();

    void checkOrUncheckSelectAll(boolean shouldCheck);
}