package personal.nmartinez.fr.virtualfootballpicker.consultwheels.view;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;

/**
 * Created by Nicolas on 03/12/2017.
 */

public interface ConsultWheelsView extends LifecycleOwner {
    void showError(String message);


    void showWheels(List<Wheel> wheels, int favoriteWheelId);

    void showFavoriteWheelChangedSuccess();

    void showFavoriteWheelChangedFailure();

    void showWheelRemovedSuccess();

    void showWheelRemovedFailure();
}
