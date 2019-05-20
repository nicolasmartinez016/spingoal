package personal.nmartinez.fr.spingoal.components.game.secondplayerstars.presenter;

import personal.nmartinez.fr.spingoal.data.models.Game;

public interface PlayerTwoSelectsStarsView {
    void goToStepThree(Game game);
    void showStarsNotSelected();

    void showStars(int stars);
}
