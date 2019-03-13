package personal.nmartinez.fr.virtualfootballpicker.game.secondplayerstars.presenter;

import personal.nmartinez.fr.virtualfootballpicker.models.Game;

public interface PlayerTwoSelectsStarsView {
    void goToStepThree(Game game);
    void showStarsNotSelected();

    void showStars(int stars);
}
