package personal.nmartinez.fr.spingoal.components.game.secondplayersecondobjective.presenter;

import personal.nmartinez.fr.spingoal.utils.ObjectivesUtils;
import personal.nmartinez.fr.spingoal.components.game.secondplayersecondobjective.view.SecondPlayerSecondObjectivePresenter;
import personal.nmartinez.fr.spingoal.data.models.Game;
import personal.nmartinez.fr.spingoal.data.models.Objective;

public class SecondPlayerSecondObjectivePresenterImpl implements SecondPlayerSecondObjectivePresenter {

    private SecondPlayerSecondObjectiveView view;
    private Game game;

    public SecondPlayerSecondObjectivePresenterImpl(SecondPlayerSecondObjectiveView view, Game game) {
        this.view = view;
        this.game = game;
    }

    @Override
    public void selectObjective() {
        if (game != null && game.getWheel() != null && game.getWheel().getObjectives() != null) {
            Objective objective = ObjectivesUtils.selectRandomObjectiveByPeriod(game.getWheel().getObjectives(),
                    Objective.SECOND_PERIOD, game.getSecondPlayerFirstObjectiveId());
            if (objective != null && view != null) {
                game.setSecondPlayerSecondObjective(objective.getName());
                view.showObjective(objective.getName());
            }
        }

    }

    @Override
    public void validateStepSix() {
        if (game != null && game.getSecondPlayerSecondObjective() != null && view != null) {
            view.goToDebrief(game);
        }
    }
}
