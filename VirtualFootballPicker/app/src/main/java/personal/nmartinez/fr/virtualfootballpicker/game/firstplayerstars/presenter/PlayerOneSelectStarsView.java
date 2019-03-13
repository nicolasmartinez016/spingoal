package personal.nmartinez.fr.virtualfootballpicker.game.firstplayerstars.presenter;

import android.arch.lifecycle.LifecycleOwner;

import personal.nmartinez.fr.virtualfootballpicker.models.Game;

public interface PlayerOneSelectStarsView extends LifecycleOwner {

    void goToStepTwo(Game game);
    void showStarsNotSelected();

    void showStars(int stars);
}
