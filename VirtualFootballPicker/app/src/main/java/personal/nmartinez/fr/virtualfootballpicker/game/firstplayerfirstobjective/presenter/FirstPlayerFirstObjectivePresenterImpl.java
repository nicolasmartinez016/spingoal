package personal.nmartinez.fr.virtualfootballpicker.game.firstplayerfirstobjective.presenter;



import personal.nmartinez.fr.virtualfootballpicker.ObjectivesManager;
import personal.nmartinez.fr.virtualfootballpicker.game.firstplayerfirstobjective.view.FirstPlayerFirstObjectivePresenter;
import personal.nmartinez.fr.virtualfootballpicker.models.Game;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;

public class FirstPlayerFirstObjectivePresenterImpl implements FirstPlayerFirstObjectivePresenter {

    private FirstPlayerFirstObjectiveView view;
    private Game game;

    public FirstPlayerFirstObjectivePresenterImpl(FirstPlayerFirstObjectiveView view, Game game) {
        this.view = view;
        this.game = game;
    }



    @Override
    public void selectObjective() {
        if (this.game != null && this.game.getWheel() != null && this.game.getWheel().getObjectives() != null) {
            Objective objective = ObjectivesManager.selectRandomObjectiveByPeriod(this.game.getWheel().getObjectives(), Objective.SECOND_PERIOD, -1);
            if (objective != null && this.view != null) {
                this.game.setFirstPlayerFirstObjective(objective.getName());
                this.game.setFirstPlayerFirstObjectiveId(objective.getId());
                this.view.showObjective(objective.getName());
            }
        }
    }

    @Override
    public void validateStepThree() {
        if (this.game != null && this.game.getFirstPlayerFirstObjective() != null) {
            this.view.goToStepFour(this.game);
        }
    }
}
