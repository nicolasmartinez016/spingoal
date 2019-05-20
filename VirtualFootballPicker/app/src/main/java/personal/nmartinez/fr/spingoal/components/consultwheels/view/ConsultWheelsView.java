package personal.nmartinez.fr.spingoal.components.consultwheels.view;

import android.arch.lifecycle.LifecycleOwner;

import java.util.List;

import personal.nmartinez.fr.spingoal.data.models.Wheel;

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
