package personal.nmartinez.fr.spingoal.components.game.firstplayerstars.presenter;

import android.arch.lifecycle.LifecycleOwner;

import personal.nmartinez.fr.spingoal.data.models.Game;

public interface PlayerOneSelectStarsView extends LifecycleOwner {

    void goToStepTwo(Game game);
    void showStarsNotSelected();

    void showStars(int stars);
}
