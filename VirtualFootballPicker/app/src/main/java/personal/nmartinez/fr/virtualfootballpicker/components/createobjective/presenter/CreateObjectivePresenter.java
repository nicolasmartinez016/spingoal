package personal.nmartinez.fr.virtualfootballpicker.components.createobjective.presenter;


import personal.nmartinez.fr.virtualfootballpicker.data.models.ObjectiveModel;

/**
 * Created by Nicolas on 09/12/2017.
 */

public interface CreateObjectivePresenter {
    void createObjective(ObjectiveModel objectiveModel);
    void setObjectivePeriod(int period);
    void setObjectiveEditable(boolean isEditable);

    void initViews();
}
