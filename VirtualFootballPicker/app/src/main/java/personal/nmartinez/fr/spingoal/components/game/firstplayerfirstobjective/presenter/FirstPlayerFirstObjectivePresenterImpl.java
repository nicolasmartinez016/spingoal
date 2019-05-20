package personal.nmartinez.fr.spingoal.components.game.firstplayerfirstobjective.presenter;



import personal.nmartinez.fr.spingoal.utils.ObjectivesUtils;
import personal.nmartinez.fr.spingoal.components.game.firstplayerfirstobjective.view.FirstPlayerFirstObjectivePresenter;
import personal.nmartinez.fr.spingoal.data.models.Game;
import personal.nmartinez.fr.spingoal.data.models.Objective;

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
            Objective objective = ObjectivesUtils.selectRandomObjectiveByPeriod(this.game.getWheel().getObjectives(), Objective.SECOND_PERIOD, -1);
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
