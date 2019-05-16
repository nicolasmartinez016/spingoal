package personal.nmartinez.fr.virtualfootballpicker.components.game.firstplayerstars.presenter;

import android.arch.lifecycle.LifecycleOwner;

import personal.nmartinez.fr.virtualfootballpicker.data.models.Game;

public interface PlayerOneSelectStarsView extends LifecycleOwner {

    void goToStepTwo(Game game);
    void showStarsNotSelected();

    void showStars(int stars);
}
