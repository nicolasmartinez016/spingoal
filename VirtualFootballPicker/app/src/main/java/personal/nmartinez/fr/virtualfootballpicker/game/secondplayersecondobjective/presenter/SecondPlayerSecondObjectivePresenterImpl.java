package personal.nmartinez.fr.virtualfootballpicker.game.secondplayersecondobjective.presenter;

import personal.nmartinez.fr.virtualfootballpicker.ObjectivesManager;
import personal.nmartinez.fr.virtualfootballpicker.game.secondplayersecondobjective.view.SecondPlayerSecondObjectivePresenter;
import personal.nmartinez.fr.virtualfootballpicker.models.Game;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;

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
            Objective objective = ObjectivesManager.selectRandomObjectiveByPeriod(game.getWheel().getObjectives(),
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
