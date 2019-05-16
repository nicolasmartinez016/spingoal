package personal.nmartinez.fr.virtualfootballpicker.components.game.secondplayerstars.presenter;

import personal.nmartinez.fr.virtualfootballpicker.data.models.Game;

public interface PlayerTwoSelectsStarsView {
    void goToStepThree(Game game);
    void showStarsNotSelected();

    void showStars(int stars);
}
