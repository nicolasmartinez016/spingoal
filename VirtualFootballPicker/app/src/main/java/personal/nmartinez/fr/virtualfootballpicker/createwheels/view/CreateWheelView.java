package personal.nmartinez.fr.virtualfootballpicker.createwheels.view;

import android.arch.lifecycle.LifecycleOwner;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;
import personal.nmartinez.fr.virtualfootballpicker.models.WheelModel;

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
