package personal.nmartinez.fr.spingoal.components.game.secondplayersecondobjective.presenter;

import personal.nmartinez.fr.spingoal.data.models.Game;

public interface SecondPlayerSecondObjectiveView {
    void showObjective(String objective);
    void goToDebrief(Game game);
}
