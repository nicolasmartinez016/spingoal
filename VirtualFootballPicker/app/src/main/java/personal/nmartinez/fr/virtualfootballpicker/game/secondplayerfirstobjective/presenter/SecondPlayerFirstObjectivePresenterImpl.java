package personal.nmartinez.fr.virtualfootballpicker.game.secondplayerfirstobjective.presenter;

import personal.nmartinez.fr.virtualfootballpicker.ObjectivesManager;
import personal.nmartinez.fr.virtualfootballpicker.game.secondplayerfirstobjective.view.SecondPlayerFirstObjectivePresenter;
import personal.nmartinez.fr.virtualfootballpicker.models.Game;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;

public class SecondPlayerFirstObjectivePresenterImpl implements SecondPlayerFirstObjectivePresenter {

    private SecondPlayerFirstObjectiveView view;
    private Game game;

    public SecondPlayerFirstObjectivePresenterImpl(SecondPlayerFirstObjectiveView view, Game game) {
        this.view = view;
        this.game = game;
    }

    @Override
    public void selectObjective() {
        if (this.game != null && this.game.getWheel() != null && this.game.getWheel().getObjectives() != null) {
            Objective objective = ObjectivesManager.selectRandomObjectiveByPeriod(this.game.getWheel().getObjectives(), Objective.FIRST_PERIOD, -1);
            if (objective != null && this.view != null) {
                this.game.setSecondPlayerFirstObjective(objective.getName());
                this.game.setSecondPlayerFirstObjectiveId(objective.getId());
                this.view.showObjective(objective.getName());
            }
        }
    }

    @Override
    public void validateStepFour() {
        if (this.game != null && this.game.getFirstPlayerFirstObjective() != null) {
            this.view.goToStepFive(this.game);
        }
    }
}
