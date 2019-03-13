package personal.nmartinez.fr.virtualfootballpicker.game.firstplayersecondobjective.presenter;

import personal.nmartinez.fr.virtualfootballpicker.NavigationManager;
import personal.nmartinez.fr.virtualfootballpicker.ObjectivesManager;
import personal.nmartinez.fr.virtualfootballpicker.game.firstplayersecondobjective.view.FirstPlayerSecondObjectivePresenter;
import personal.nmartinez.fr.virtualfootballpicker.models.Game;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;

public class FirstPlayerSecondObjectivePresenterImpl implements FirstPlayerSecondObjectivePresenter {

    private FirstPlayerSecondObjectiveView view;
    private Game game;

    public FirstPlayerSecondObjectivePresenterImpl(FirstPlayerSecondObjectiveView view, Game game) {
        this.view = view;
        this.game = game;
    }

    @Override
    public void selectObjective() {
        if (game != null && view != null && game.getWheel() != null && game.getWheel().getObjectives() != null) {
            Objective objective = ObjectivesManager.selectRandomObjectiveByPeriod(game.getWheel().getObjectives(),
                    Objective.SECOND_PERIOD, game.getFirstPlayerFirstObjectiveId());
            if (objective != null) {
                game.setFirstPlayerSecondObjective(objective.getName());
                view.showObjective(objective.getName());
            }

        }
    }

    @Override
    public void validateStepFive() {
        if (view != null && game != null && game.getFirstPlayerSecondObjective() != null) {
            view.goToStepSix(game);
        }
    }
}
